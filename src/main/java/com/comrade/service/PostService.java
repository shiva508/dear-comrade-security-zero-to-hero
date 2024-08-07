package com.comrade.service;

import java.util.List;

import com.comrade.entity.Post;

public interface PostService {
	public Post save(Post post);

	public List<Post> posts();

}
