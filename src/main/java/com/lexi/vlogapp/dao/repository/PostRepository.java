package com.lexi.vlogapp.dao.repository;

import com.lexi.vlogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
