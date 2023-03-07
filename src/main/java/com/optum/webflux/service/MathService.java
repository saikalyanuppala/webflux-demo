package com.optum.webflux.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.optum.webflux.dto.Response;
import com.optum.webflux.util.Util;

@Service
public class MathService {

	public Response findSquare(Integer number) {
		return new Response(number * number);
	}

	public List<Response> multiplicationTable(int input) {
		return IntStream.range(1, 10)
				.peek(i -> Util.sleepSeconds(1))
				.peek(s -> System.out.println("Math service processing "+s+" for input "+input))
				.mapToObj(i -> new Response(i * input))
				.toList();

	}
}
