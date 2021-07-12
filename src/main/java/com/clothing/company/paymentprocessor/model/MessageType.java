package com.clothing.company.paymentprocessor.model;

public enum MessageType {
	
	Online("Online"), Offline("Offline");

	private final String messageType;
	
	MessageType(String messageType){
		this.messageType = messageType;
	}
	
	public String toString() {
		return this.getMessageType();
	}
	
	public String getMessageType() {
		return this.messageType;
	}
}
