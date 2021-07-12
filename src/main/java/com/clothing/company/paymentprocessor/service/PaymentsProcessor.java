package com.clothing.company.paymentprocessor.service;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.clothing.company.paymentprocessor.domain.model.AccountsDO;
import com.clothing.company.paymentprocessor.domain.model.PaymentsDO;
import com.clothing.company.paymentprocessor.exception.AccountNotFoundException;
import com.clothing.company.paymentprocessor.model.ErrorDesc;
import com.clothing.company.paymentprocessor.model.ErrorType;
import com.clothing.company.paymentprocessor.model.MessageType;
import com.clothing.company.paymentprocessor.repository.PaymentsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentsProcessor implements Runnable {

	private final Logger logger = LoggerFactory.getLogger(PaymentsProcessor.class);

	private RestTemplate restTemplate;

	private PaymentsRepository paymentsRepository;

	private EntityManager entityManager;

	private String message;

	private MessageType messageType;

	public PaymentsProcessor(String message, MessageType messageType, RestTemplate restTemplate,
			PaymentsRepository paymentsRepository, EntityManager entityManager) {
		this.message = message;
		this.messageType = messageType;
		this.restTemplate = restTemplate;
		this.paymentsRepository = paymentsRepository;
		this.entityManager = entityManager;
	}

	@Override
	public void run() {
		logger.info(String.format("Processing %s message: msg -> %s", this.messageType, this.message));
		this.processPayments();
	}

	public void processPayments() {
		PaymentsDO payment = null;
		try {
			payment = getPaymentDO(this.message);
			payment.setCreatedOn(LocalDateTime.now());

			if (this.messageType.equals(MessageType.Online)) {
				ResponseEntity<Object> respEntity = restTemplate.postForEntity("http://localhost:9001/payment", payment,
						Object.class);
				if (respEntity.getStatusCode() == HttpStatus.OK) {
					persistPaymentInfo(payment);
				}
			} else if (this.messageType.equals(MessageType.Offline)) {
				persistPaymentInfo(payment);
			}
		} catch (RestClientException exception) {
			ErrorDesc error = new ErrorDesc(payment.getPaymentID(), ErrorType.network, exception.getMessage());
			ResponseEntity<Object> resp = restTemplate.postForEntity("http://localhost:9001/log", error, Object.class);
			logger.error("Error while processing payment... Error - " + resp.toString());
		} catch (AccountNotFoundException exception) {
			ErrorDesc error = new ErrorDesc(payment.getPaymentID(), ErrorType.database, exception.getMessage());
			ResponseEntity<Object> resp = restTemplate.postForEntity("http://localhost:9001/log", error, Object.class);
			logger.error("Error while processing payment... Error - " + resp.toString());
		} catch (Exception exception) {
			ErrorDesc error = new ErrorDesc(payment.getPaymentID(), ErrorType.other, exception.getMessage());
			ResponseEntity<Object> resp = restTemplate.postForEntity("http://localhost:9001/log", error, Object.class);
			logger.error("Error while processing payment... Error - " + resp.toString());
		}
	}

	@Transactional
	public void persistPaymentInfo(PaymentsDO payment) {
		AccountsDO accountsDO = entityManager.find(AccountsDO.class, payment.getAccountID());
		if (accountsDO == null) {
			throw new AccountNotFoundException("Account not found:" + payment.getAccountID());
		}
		LocalDateTime localDateTime = LocalDateTime.now();
		accountsDO.setLastPaymentDate(localDateTime);
		payment.setAccountsDO(accountsDO);
		paymentsRepository.save(payment);
	}

	public PaymentsDO getPaymentDO(String paymentMessage) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		PaymentsDO payment = mapper.readValue(paymentMessage, PaymentsDO.class);
		return payment;
	}

}
