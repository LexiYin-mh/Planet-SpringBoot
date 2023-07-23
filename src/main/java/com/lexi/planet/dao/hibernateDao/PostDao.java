package com.lexi.planet.dao.hibernateDao;

import com.lexi.planet.entity.Post;

public interface PostDao extends GenericDao<Post, Long>{

    Integer getLikeCount(Long id);

    Post findByPostContentContaining(String keyword);
}
