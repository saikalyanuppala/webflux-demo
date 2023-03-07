package com.optum.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optum.webflux.dto.model.User;
import com.optum.webflux.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class ReactiveUserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public Mono<User> getUserById(@PathVariable Integer id){
		return userService.findUserById(id);
	}
	
	@GetMapping
	public Flux<User> getAllUsers(){
		return userService.findAllUsers();
	}
	
	@PostMapping
	public Mono<User> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
}
