package com.dharuan.springmongo.services;

import java.time.Instant;
import java.util.List;

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

	public List<Post> findByTitle(String title) {
		// return repository.findByTitleContainingIgnoreCase(title);
		return repository.findByTitle(title);
	}

	public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
		maxDate = maxDate.plusSeconds(86400); // adjust max date to next day at midnight
		return repository.fullSearchByDate(text, minDate, maxDate);
	}
}
