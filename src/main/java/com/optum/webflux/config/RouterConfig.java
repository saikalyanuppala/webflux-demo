package com.optum.webflux.config;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.optum.webflux.dto.InputFailedValidationResponse;
import com.optum.webflux.exception.InputValidationException;

import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

	@Autowired
	private RouterHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> pathBasedRouting(){
		return RouterFunctions.route().path("router", this::serverResponseRouterFunction).build();
	}

	private RouterFunction<ServerResponse> serverResponseRouterFunction() {
		return RouterFunctions.route()
				.GET("square/{input}", handler::squareHandler)
				.GET("square/{input}/validation", handler::squareHandlerWithValidation)
				.GET("table/{input}", handler::tableHandler)
				.GET("table/{input}/stream", handler::tableStreamHandler)
				.POST("multiply", handler::multiplyHandler)
				.onError(InputValidationException.class, exceptionHandler())
				.build();
	}
	
	// commented to show path based routing
//	@Bean
//	public RouterFunction<ServerResponse> serverResponseRouterFunction() {
//		return RouterFunctions.route()
//				.GET("router/square/{input}", handler::squareHandler)
//				.GET("router/square/{input}/validation", handler::squareHandlerWithValidation)
//				.GET("router/table/{input}", handler::tableHandler)
//				.GET("router/table/{input}/stream", handler::tableStreamHandler)
//				.POST("router/multiply", handler::multiplyHandler)
//				.onError(InputValidationException.class, exceptionHandler())
//				.build();
//	}
	
	private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
		return (err,req) -> {
			InputValidationException ex=(InputValidationException)err;
			InputFailedValidationResponse response=new InputFailedValidationResponse();
			response.setErrorCode(ex.getErrorCode());
			response.setInput(ex.getInput());
			response.setMessage(ex.getMessage());
			return ServerResponse.badRequest().bodyValue(response);
		};
	}
}
