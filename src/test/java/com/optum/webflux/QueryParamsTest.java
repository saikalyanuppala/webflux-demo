package com.optum.webflux;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class QueryParamsTest extends BaseTest{

	
	@Test
	void queryParamsTest() {
	String query="http://localhost:8080/params/test?num1={num1}&num2={num2}";
	URI uri=UriComponentsBuilder.fromUriString(query).build(10,20);
	Flux<Integer> response=webClient.get()
			                       .uri(uri)
			                       .retrieve()
			                       .bodyToFlux(Integer.class)
			                       .doOnNext(System.out::println);

	StepVerifier.create(response)
	            .expectNext(10)
	            .expectNext(20)
	            .verifyComplete();
	
	}
	
	@Test
	void queryParamsTest2() {
	Flux<Integer> response=webClient.get()
			                       .uri(uriBuilder -> uriBuilder.path("params/test").query("num1={num1}&num2={num2}").build(15,30))
			                       .retrieve()
			                       .bodyToFlux(Integer.class)
			                       .doOnNext(System.out::println);

	StepVerifier.create(response)
	            .expectNext(15)
	            .expectNext(30)
	            .verifyComplete();
	
	}
}
