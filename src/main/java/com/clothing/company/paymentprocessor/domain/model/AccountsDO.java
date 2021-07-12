package com.clothing.company.paymentprocessor.domain.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountsDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountID;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "accountsDO")
	private Set<PaymentsDO> paymentsDO;

	@Column(name = "name")
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "birthdate")
	private Date birthdate;

	@Column(name = "last_payment_date")
	private LocalDateTime lastPaymentDate;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public LocalDateTime getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(LocalDateTime lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
}
