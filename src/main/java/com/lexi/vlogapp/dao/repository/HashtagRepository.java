package com.lexi.vlogapp.dao.repository;

import com.lexi.vlogapp.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByHashtagContent(String hashtagContent);

    Boolean existsByHashtagContent(String hashtagContent);

}
