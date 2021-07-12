package com.clothing.company.paymentprocessor.domain.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payments")
public class PaymentsDO {

	@Id
	@Column(name = "payment_id")
	@JsonAlias("payment_id")
	private String paymentID;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private AccountsDO accountsDO;

	@Column(name = "account_id", insertable = false, updatable = false)
	@JsonAlias("account_id")
	private Integer accountID;

	@Column(name = "payment_type", nullable = false)
	@JsonAlias("payment_type")
	private String paymentType;

	@Column(name = "credit_card")
	@JsonAlias("credit_card")
	private String creditCard;

	@Column(name = "amount", nullable = false)
	private Integer amount;

	@JsonIgnore
	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@JsonIgnore
	@Column(name = "delay")
	private Integer delay;

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public AccountsDO getAccountsDO() {
		return accountsDO;
	}

	public void setAccountsDO(AccountsDO accountsDO) {
		this.accountsDO = accountsDO;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

}
