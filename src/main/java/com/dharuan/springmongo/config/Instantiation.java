package com.dharuan.springmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dharuan.springmongo.domain.Post;
import com.dharuan.springmongo.domain.User;
import com.dharuan.springmongo.dto.AuthorDTO;
import com.dharuan.springmongo.repository.PostRepository;
import com.dharuan.springmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();

		var maria = new User(null, "Maria Brown", "maria@gmail.com");
		var alex = new User(null, "Alex Green", "alex@gmail.com");
		var bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		var post1 = new Post(null, Instant.parse("2018-03-21T10:34:32Z"), "Partiu Viagem!",
				"Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));

		var post2 = new Post(null, Instant.parse("2018-03-23T14:34:32Z"), "Bom dia!", "Acordei feliz hoje!",
				new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));

		userRepository.save(maria);
	}

}
