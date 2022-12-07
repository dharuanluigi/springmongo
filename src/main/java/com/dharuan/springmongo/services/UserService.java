package com.dharuan.springmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharuan.springmongo.domain.User;
import com.dharuan.springmongo.dto.UserDTO;
import com.dharuan.springmongo.repository.UserRepository;
import com.dharuan.springmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		var user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found in repository"));
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
