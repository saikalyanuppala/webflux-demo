package com.optum.webflux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optum.webflux.dto.Response;
import com.optum.webflux.service.MathService;

@RestController
@RequestMapping("/math")
public class MathController {

	@Autowired
	private MathService mathService;

	@GetMapping("/square/{number}")
	public Response findSquare(@PathVariable Integer number) {
		return mathService.findSquare(number);
	}

	@GetMapping("/table/{number}")
	public List<Response> multiplicationTable(@PathVariable Integer number) {
		return mathService.multiplicationTable(number);
	}
}
