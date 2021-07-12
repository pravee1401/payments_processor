package com.clothing.company.paymentprocessor.kafka;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clothing.company.paymentprocessor.model.MessageType;
import com.clothing.company.paymentprocessor.repository.PaymentsRepository;
import com.clothing.company.paymentprocessor.service.PaymentsProcessor;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PaymentsRepository paymentsRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	@Qualifier("fixedThreadPool")
	private ExecutorService executorService;

	@KafkaListener(topics = "online", groupId = "payments_group_id_v1")
	public void consumeOnlinePaymentMessages(String message) {
//		logger.info(String.format("Consumed online message -> %s", message));
		executorService.submit(
				new PaymentsProcessor(message, MessageType.Online, restTemplate, paymentsRepository, entityManager));
	}

	@KafkaListener(topics = "offline", groupId = "payments_group_id_v1")
	public void consumeOfflinePaymentMessages(String message) throws IOException {
//		logger.info(String.format("Consumed offline message -> %s", message));
		executorService.submit(
				new PaymentsProcessor(message, MessageType.Offline, restTemplate, paymentsRepository, entityManager));
	}
}
