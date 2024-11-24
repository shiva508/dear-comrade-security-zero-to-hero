package com.comrade.service;

import java.util.List;

import com.comrade.annotation.PostReaderAuthorize;
import com.comrade.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comrade.entity.Post;
import com.comrade.repository.PostRepository;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	@Transactional
	public Post save(Post post) {
		return postRepository.saveAndFlush(post);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Post> posts() {
		return postRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	@PostReaderAuthorize
	public Post findByPostId(Long postId) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("PostService::findByPostId::name={}",name);
		return postRepository.findById(postId).orElseThrow(()-> new RecordNotFoundException("Post not found"));
	}

}
