package com.clothing.company.paymentprocessor.model;

public class ErrorDesc {

	private String payment_id;

	private ErrorType error_type;

	private String error_description;

	public ErrorDesc(String payment_id, ErrorType error_type, String error_description) {
		this.payment_id = payment_id;
		this.error_type = error_type;
		this.error_description = error_description;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public ErrorType getError_type() {
		return error_type;
	}

	public String getError_description() {
		return error_description;
	}

}
