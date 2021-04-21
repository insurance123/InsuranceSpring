package com.lti.dto;

public class RegisterStatus {
	
	private StatusType registerStatus;
	private String registerMessage;
	
	public static enum StatusType {
		SUCCESS, FAILURE;
	}

	public StatusType getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(StatusType registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getRegisterMessage() {
		return registerMessage;
	}

	public void setRegisterMessage(String registerMessage) {
		this.registerMessage = registerMessage;
	}

}
