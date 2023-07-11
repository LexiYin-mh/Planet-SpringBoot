package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dao.hibernateDao.PostDao;
import com.lexi.vlogapp.entity.Post;
import com.lexi.vlogapp.dao.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostService implements PostDao {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Set<Post> findAll() {
        return null;
    }

    @Override
    public Post save(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Override
    public Post update(Post model) {
        return null;
    }

    @Override
    public boolean delete(Post model) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public Integer getLikeCount(Long id) {
        return null;
    }

    @Override
    public Post findByPostContentContaining(String keyword) {
        return null;
    }

}
