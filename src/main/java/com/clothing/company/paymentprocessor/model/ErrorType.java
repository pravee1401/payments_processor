package com.clothing.company.paymentprocessor.model;

public enum ErrorType {
	
	database("database"),
	network("network"),
	other("other");

	private final String errorType;
	
	ErrorType(String errorType){
		this.errorType = errorType;
	}
	
	@Override
	public String toString() {
		return this.getErrorType();
	}
	
	public String getErrorType() {
		return errorType;
	}
}
