package com.dharuan.springmongo.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dharuan.springmongo.domain.Post;
import com.dharuan.springmongo.resources.util.URL;
import com.dharuan.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		var post = service.findById(id);
		return ResponseEntity.ok(post);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
		title = URL.decodeParam(title);
		var posts = service.findByTitle(title);
		return ResponseEntity.ok(posts);
	}

	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		var min = URL.convertDate(minDate, Instant.parse("1900-01-01T00:00:00Z"));
		var max = URL.convertDate(maxDate, Instant.now());

		var posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok(posts);
	}
}
