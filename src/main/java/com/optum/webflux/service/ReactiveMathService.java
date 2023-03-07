package com.optum.webflux.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.optum.webflux.dto.MultiplyRequestDTO;
import com.optum.webflux.dto.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

	public Mono<Response> findSquare(Integer number) {
		// Mono.fromSupplier(() -> number* number).map(Response::new);
		return Mono.fromSupplier(() -> new Response(number * number));
	}

	public Flux<Response> multiplicationTable(Integer number) {
		return Flux.range(1, 10)
				//.doOnNext(i -> Util.sleepSeconds(1))
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(s -> System.out.println("reactive math processing "+s+" for input "+number))
				.map(i -> new Response(i * number));
	}

	public Mono<Response> multiply(Mono<MultiplyRequestDTO> dtoMono) {
		return dtoMono.map( dto -> dto.getFirst()* dto.getSecond()).map(Response::new);
	}
}
