package com.comrade.service;

import java.util.List;

import com.comrade.entity.Post;

public interface PostService {

	Post save(Post post);
	List<Post> posts();
	Post findByPostId(Long postId);

}
