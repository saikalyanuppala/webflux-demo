package com.optum.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.optum.webflux.dto.InputFailedValidationResponse;
import com.optum.webflux.dto.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ExchangeTest extends BaseTest{

	//exchange == retrieve + http status codes
	@Test
	void badRequestTest() {
		 Mono<Object> response= webClient.get()
                          //.uri("reactive-math/square/{number}/throw",2)
		                  .uri("reactive-math/square/{number}/throw",12)
		                  .exchangeToMono(this::exchange)
		                  .doOnNext(System.out::println)
		                  .doOnError(err -> System.out.println(err.getMessage()));
		 
		 StepVerifier.create(response)
		             .expectNextCount(1)
		             .verifyComplete();
	}
	
	private Mono<Object> exchange(ClientResponse cr){
		if(cr.rawStatusCode() == 400)
			return cr.bodyToMono(InputFailedValidationResponse.class);
		else
			return cr.bodyToMono(Response.class);
	}
}
