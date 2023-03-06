package com.optum.webflux.exception;

public class InputValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5632775423741780848L;

	private static final String MSG = "allowed rane is 10 to 20";
	private Integer input;
	private Integer errorCode = 100;

	public InputValidationException(Integer input) {
		super(MSG);
		this.input = input;
	}

	public Integer getInput() {
		return input;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
}
