package com.dharuan.springmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharuan.springmongo.domain.Post;
import com.dharuan.springmongo.repository.PostRepository;
import com.dharuan.springmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		var post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found in repository"));
	}
}
