package com.comrade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comrade.entity.Post;
import com.comrade.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Post post) {
		Post savedPost = postService.save(post);
		return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> posts(){
		List<Post> posts = postService.posts();
		
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
}
