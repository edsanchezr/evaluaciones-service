package com.evaluaciones.common.exception;

public class TransactionDataException extends RuntimeException {
	
	private static final long serialVersionUID = 2495841579046754899L;
	
	private final String code;
	private final String message;
	private final String action;
	
	public TransactionDataException (String code, String message, String action) {
		this.code = code;
		this.message = message;
		this.action = action;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getAction() {
		return action;
	}
}
