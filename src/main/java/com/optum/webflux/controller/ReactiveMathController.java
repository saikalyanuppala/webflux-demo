package com.optum.webflux.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optum.webflux.dto.MultiplyRequestDTO;
import com.optum.webflux.dto.Response;
import com.optum.webflux.exception.InputValidationException;
import com.optum.webflux.service.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-math")
public class ReactiveMathController {

	@Autowired
	private ReactiveMathService reactiveMathService;

	@GetMapping("/square/{number}")
	public Mono<Response> findSquare(@PathVariable Integer number) {
		return reactiveMathService.findSquare(number);
	}

	@GetMapping("/square/{number}/throw")
	public Mono<Response> findSquareThrow(@PathVariable Integer number) {
		if (number < 10 || number > 20)
			throw new InputValidationException(number);
		return reactiveMathService.findSquare(number);
	}

	@GetMapping("/square/{number}/mono-error")
	public Mono<Response> findSquareMonoError(@PathVariable Integer number) {
		return Mono.just(number).handle((i, sink) -> {
			if (i >= 10 && i <= 20)
				sink.next(i);
			else
				sink.error(new InputValidationException(i));
		}).cast(Integer.class).flatMap(i -> reactiveMathService.findSquare(number));
	}
	
	@GetMapping("/square/{number}/assignment")
	public Mono<ResponseEntity<Response>> assignment(@PathVariable Integer number) {
		return Mono.just(number)
				.filter( i -> i>=10 && i<=20)
				.flatMap(e -> reactiveMathService.findSquare(e))
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@GetMapping("/table/{number}")
	public Flux<Response> multiplicationTable(@PathVariable Integer number) {
		return reactiveMathService.multiplicationTable(number);
	}

	@GetMapping(value = "/table/{number}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Response> multiplicationTableStream(@PathVariable Integer number) {
		return reactiveMathService.multiplicationTable(number);
	}

	@PostMapping("/multiply")
	public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDTO> dto,
			@RequestHeader Map<String, String> headers) {
		System.out.println("headers " + headers);
		return reactiveMathService.multiply(dto);
	}
}
