package com.optum.webflux;

import org.junit.jupiter.api.Test;

import com.optum.webflux.dto.MultiplyRequestDTO;
import com.optum.webflux.dto.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PostRequestTest extends BaseTest{

	@Test
	void postTest() {
		Mono<Response> response=webClient
				 .post()
		         .uri("reactive-math/multiply")
		         .bodyValue(dto(12,12))
		         .retrieve()
		         .bodyToMono(Response.class)
		         .doOnNext(System.out::println);
		
		StepVerifier.create(response)
		            .expectNextMatches(res -> res.getOutput() == 144)
		            .verifyComplete();
	}
	
	private MultiplyRequestDTO dto(int a,int b) {
		return new MultiplyRequestDTO(a,b);
	}
}
