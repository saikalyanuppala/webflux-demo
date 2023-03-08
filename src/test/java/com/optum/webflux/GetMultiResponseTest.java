package com.optum.webflux;

import org.junit.jupiter.api.Test;

import com.optum.webflux.dto.Response;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class GetMultiResponseTest extends BaseTest {


	@Test
	void fluxTest() {
		Flux<Response> response = webClient.get()
				.uri("reactive-math/table/{number}", 6)
				.retrieve()
				.bodyToFlux(Response.class)
				.doOnNext(System.out::println);

		StepVerifier.create(response).expectNextCount(10).verifyComplete();
	}

	@Test
	void fluxStreamTest() {
		Flux<Response> response = webClient.get()
				.uri("reactive-math/table/{number}/stream", 6)
				.retrieve()
				.bodyToFlux(Response.class)
				.doOnNext(System.out::println);
		
		StepVerifier.create(response).expectNextCount(10).verifyComplete();
	}
}
