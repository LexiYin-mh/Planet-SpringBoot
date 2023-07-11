package com.lexi.vlogapp.dao.hibernateDao;

import com.lexi.vlogapp.entity.Post;

public interface PostDao extends GenericDao<Post, Long>{

    Integer getLikeCount(Long id);

    Post findByPostContentContaining(String keyword);
}
