package com.clothing.company.paymentprocessor.exception;

public class AccountNotFoundException extends RuntimeException{

	public AccountNotFoundException(String message) {
		super(message);
	}
}
