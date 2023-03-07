package com.optum.webflux.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2888338322698931833L;
	private static final String MESSAGE = "User Not found for input ";
	private Integer input;
	private Integer errorCode = 400;

	public UserNotFoundException(Integer input) {
		super(MESSAGE);
		this.input = input;
	}
}
