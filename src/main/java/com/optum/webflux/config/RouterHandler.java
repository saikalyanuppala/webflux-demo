package com.optum.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.optum.webflux.dto.MultiplyRequestDTO;
import com.optum.webflux.dto.Response;
import com.optum.webflux.exception.InputValidationException;
import com.optum.webflux.service.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RouterHandler {

	@Autowired
	private ReactiveMathService mathService;

	public Mono<ServerResponse> squareHandler(ServerRequest request) {
		Integer input = Integer.parseInt(request.pathVariable("input"));
		Mono<Response> monoResponse = mathService.findSquare(input);
		return ServerResponse.ok().body(monoResponse, Response.class);
	}
	
	public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest request) {
		Integer input = Integer.parseInt(request.pathVariable("input"));
		if(input <10 || input >20)
			return Mono.error(new InputValidationException(input));
		
		Mono<Response> monoResponse = mathService.findSquare(input);
		return ServerResponse.ok().body(monoResponse, Response.class);
	}

	public Mono<ServerResponse> tableHandler(ServerRequest request) {
		Integer input = Integer.parseInt(request.pathVariable("input"));
		Flux<Response> fluxResponse = mathService.multiplicationTable(input);
		return ServerResponse.ok().body(fluxResponse, Response.class);
	}
	
	public Mono<ServerResponse> tableStreamHandler(ServerRequest request) {
		Integer input = Integer.parseInt(request.pathVariable("input"));
		Flux<Response> fluxResponse = mathService.multiplicationTable(input);
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(fluxResponse, Response.class);
	}
	
	public Mono<ServerResponse> multiplyHandler(ServerRequest request) {
		System.out.println(request.headers());
		Mono<MultiplyRequestDTO> bodyToMono = request.bodyToMono(MultiplyRequestDTO.class);
		Mono<Response> monoResponse = mathService.multiply(bodyToMono);
		return ServerResponse.ok().body(monoResponse,Response.class);
	}
}
