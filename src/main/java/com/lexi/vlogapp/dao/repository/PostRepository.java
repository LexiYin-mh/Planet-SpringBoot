package com.lexi.vlogapp.dao.repository;

import com.lexi.vlogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    // This retrieval is case sensitive
    Set<Post> findByPostContentContaining(String keyword);

    Set<Post> findByUserId(Long id);
}
