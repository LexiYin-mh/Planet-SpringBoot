package com.lexi.vlogapp.repository;

import com.lexi.vlogapp.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
