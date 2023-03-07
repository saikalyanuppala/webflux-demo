package com.optum.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

	@Autowired
	private RouterHandler handler;

	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunction() {
		return RouterFunctions.route()
				.GET("router/square/{input}", handler::squareHandler)
				.GET("router/table/{input}", handler::tableHandler)
				.GET("router/table/{input}/stream", handler::tableStreamHandler)
				.POST("router/multiply", handler::multiplyHandler)
				.build();
	}
}
