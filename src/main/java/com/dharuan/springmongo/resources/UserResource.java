package com.dharuan.springmongo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dharuan.springmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		var user = new User("1231", "Maria", "email@email.com");
		var user2 = new User("1111", "Alex", "alex@email.com");

		var users = new ArrayList<User>();
		users.add(user);
		users.add(user2);

		return ResponseEntity.ok(users);
	}
}
