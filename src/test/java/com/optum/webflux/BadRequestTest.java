package com.optum.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.optum.webflux.dto.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BadRequestTest extends BaseTest {

	@Test
	void badRequestTest() {
		 Mono<Response> response= webClient.get()
		                  .uri("reactive-math/square/{number}/throw",2)
		                  .retrieve()
		                  .bodyToMono(Response.class)
		                  .doOnNext(System.out::println)
		                  .doOnError(err -> System.out.println(err.getMessage()));
		 
		 StepVerifier.create(response)
		             .verifyError(WebClientResponseException.BadRequest.class);
	}
}
