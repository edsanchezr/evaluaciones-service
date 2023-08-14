package com.evaluaciones.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler (TransactionDataException.class)
	public ResponseEntity<ErrorResponse> transactionDataException 
		(TransactionDataException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setError(ex.getAction(), ex.getCode(), ex.getMessage());
		return new ResponseEntity <ErrorResponse> (response, HttpStatus.CONFLICT);
	}
}
