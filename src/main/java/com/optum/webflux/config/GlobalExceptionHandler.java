package com.optum.webflux.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.optum.webflux.dto.InputFailedValidationResponse;
import com.optum.webflux.exception.InputValidationException;
import com.optum.webflux.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
		return ResponseEntity.badRequest().body(buildErrorResponse(ex.getErrorCode(), ex.getMessage(), ex.getInput()));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<InputFailedValidationResponse> handleUserNotFoundException(UserNotFoundException ex) {
		return ResponseEntity.badRequest().body(buildErrorResponse(ex.getErrorCode(), ex.getMessage(), ex.getInput()));
	}
	
	public InputFailedValidationResponse buildErrorResponse(Integer code,String message,Integer input) {
		InputFailedValidationResponse response = new InputFailedValidationResponse();
		response.setErrorCode(code);
		response.setInput(input);
		response.setMessage(message);
		return response;
	}
}
