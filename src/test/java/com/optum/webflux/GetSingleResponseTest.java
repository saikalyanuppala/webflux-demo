package com.optum.webflux;

import org.junit.jupiter.api.Test;

import com.optum.webflux.dto.Response;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class GetSingleResponseTest extends BaseTest {

	@Test
	void blockTest() {
		Response response=webClient.get()
		         .uri("reactive-math/square/{number}",50)
		         .retrieve()
		         .bodyToMono(Response.class) //Mono<Response>
		         .block();
		
		System.out.println(response);
		         
	}
	
	@Test
	void stepVerifier() {
		Mono<Response> response=webClient.get()
		         .uri("reactive-math/square/{number}",50)
		         .retrieve()
		         .bodyToMono(Response.class); //Mono<Response>
		
		StepVerifier.create(response)
		            .expectNextMatches(res -> res.getOutput()==2500)
		            .verifyComplete();
		
		         
	}
}
