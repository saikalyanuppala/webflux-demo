package com.optum.webflux.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.optum.webflux.dto.model.User;
import com.optum.webflux.exception.UserNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	private static List<User> users = new ArrayList<>();

	public Mono<User> findUserById(Integer id) {
		return Mono.create(sink -> {
			Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
			if (user.isPresent())
				sink.success(user.get());
			else
				sink.error(new UserNotFoundException(id));
		});
	}

	public Flux<User> findAllUsers() {
		return Flux.fromIterable(users);
	}

	public Mono<User> saveUser(User user) {
		users.add(user);
		return Mono.just(user);
	}
}