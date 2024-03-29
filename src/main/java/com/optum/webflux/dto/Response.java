package com.optum.webflux.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response {

	private LocalDate date = LocalDate.now();
	private Integer output;

	public Response(Integer output) {
		super();
		this.output = output;
	}

}
