package com.optum.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebclientConfig {

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.defaultHeader("key1", "value1")
				.baseUrl("http://localhost:8080")
				.filter(this::sessionToken)
				.build();
	}
	
	private Mono<ClientResponse> sessionToken(ClientRequest req,ExchangeFunction ex){
		System.out.println("generating session token");
		ClientRequest request=ClientRequest.from(req).headers(h -> h.setBearerAuth("long-jwt-token")).build();
		return ex.exchange(request);
	}
}
