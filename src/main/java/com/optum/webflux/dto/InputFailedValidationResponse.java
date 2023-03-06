package com.optum.webflux.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InputFailedValidationResponse {

	private Integer errorCode;
	private Integer input;
	private String message;
}
