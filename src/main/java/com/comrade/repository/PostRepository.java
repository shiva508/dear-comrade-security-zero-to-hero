package com.comrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.comrade.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
