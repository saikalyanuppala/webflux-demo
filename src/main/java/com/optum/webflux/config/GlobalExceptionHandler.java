package com.optum.webflux.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.optum.webflux.dto.InputFailedValidationResponse;
import com.optum.webflux.exception.InputValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
		InputFailedValidationResponse input = new InputFailedValidationResponse();
		input.setErrorCode(ex.getErrorCode());
		input.setInput(ex.getInput());
		input.setMessage(ex.getMessage());
		return ResponseEntity.badRequest().body(input);
	}
}
