package com.optum.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/params")
public class ParamsController {

	@GetMapping("/test")
	public Flux<Integer> searchJobs(@RequestParam Integer num1, @RequestParam Integer num2) {
		return Flux.just(num1,num2);
	}
}
