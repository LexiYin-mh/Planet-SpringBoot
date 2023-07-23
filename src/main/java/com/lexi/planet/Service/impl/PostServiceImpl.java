package com.lexi.planet.Service.impl;

import com.lexi.planet.Service.PostService;
import com.lexi.planet.dao.repository.PostRepository;
import com.lexi.planet.dao.repository.UserRepository;
import com.lexi.planet.dto.PostDto;
import com.lexi.planet.dto.UserDto;
import com.lexi.planet.entity.Post;
import com.lexi.planet.entity.User;
import com.lexi.planet.exception.InvalidCredentialsException;
import com.lexi.planet.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class PostServiceImpl implements PostService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    int DFAULT_POST_LIMIT = 3;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperUtil mapper;

    @Override
    public PostDto create(PostDto postDto) {
        // Check if there is any sensitive content;
        validateContent(postDto.getPostContent());

        // Check if User is valid
        validateUser(postDto.getUserDto());

        Post post = mapper.convertPostDtoToEntity(postDto);
        Post savedPost = postRepository.save(post);
        PostDto savedPostDto = mapper.convertPostToDto(savedPost);
        return savedPostDto;
    }

    private void validateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new InvalidCredentialsException("User Not Found!"));
    }

    @Override
    public PostDto update(PostDto postDto) {
        /*
        Check if there is any sensitive content;
         */
        // Check Method;
        validateContent(postDto.getPostContent());

        Post post = mapper.convertPostDtoToEntity(postDto);
        Post updatedPost = postRepository.save(post);
        PostDto updatedPostDto = mapper.convertPostToDto(updatedPost);
        return updatedPostDto;
    }

    private void validateContent(String postContent) {
        // Implement your logic here to check for sensitive content
        // You can throw an exception if the content is not valid
    }

    @Override
    public Boolean delete(PostDto postDto) {
        Boolean flag = false;
        Post post = mapper.convertPostDtoToEntity(postDto);
        if (post.getId() == null || postRepository.existsById(post.getId())) {
            logger.error("========== Post does not exist!");
        }
        postRepository.delete(post);
        flag = true;
        return flag;
    }

    @Override
    public Boolean deleteById(Long id) {
        Boolean flag = false;
        try {
            postRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            logger.error("Post with id = {} does not exist!", id);
        }
        flag = true;
        return flag;
    }

    @Override
    public PostDto getById(Long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (!postOpt.isPresent())
            throw new NoSuchElementException("Post with id " + id + " not found");
        Post post = postOpt.get();
        return mapper.convertPostToDto(post);
    }

    @Override
    public Set<PostDto> getAll() {
        List<Post> posts = postRepository.findAll();
        Set<PostDto> postDtos = new HashSet<>();
        for (Post post : posts) {
            PostDto postDto = mapper.convertPostToDto(post);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @Override
    public List<PostDto> getUserRecentPosts(Long userId, int limit) {

        Pageable pageable = PageRequest.of(0, limit);
        List<Post> recentPosts = postRepository.findTopByUserIdOrderByPostDateDesc(userId, pageable);

        List<PostDto> recentPostDtos = new ArrayList<>();
        for (Post post : recentPosts) {
            PostDto postDto = mapper.convertPostToDto(post);
            recentPostDtos.add(postDto);
        }
        return recentPostDtos;
    }

    @Override
    public List<PostDto> getUserRecentPosts(Long userId) {
        return getUserRecentPosts(userId, DFAULT_POST_LIMIT);
    }

    @Override
    public List<PostDto> getPopularPosts(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Post> popularPosts = postRepository.findTopPosts(pageable);
        List<PostDto> popularPostDtos = new ArrayList<>();
        for (Post post : popularPosts) {
            PostDto postDto = mapper.convertPostToDto(post);
            popularPostDtos.add(postDto);
        }
        return popularPostDtos;
    }

    @Override
    public List<PostDto> getPopularPosts() {
        return getPopularPosts(DFAULT_POST_LIMIT);
    }

    @Override
    public PostDto getPostAndUserAndHashtagsByPostId(Long postId) {
        Optional<Post> postOpt = postRepository.findWithUserAndHashtagsById(postId);
        if (!postOpt.isPresent())
            throw new NoSuchElementException("Post not found with id" + postId);
        Post post = postOpt.get();
        return mapper.convertPostToDto(post);
    }

    @Override
    public Set<PostDto> searchPostByContent(String keyword) {
        Set<Post> posts = postRepository.findByPostContentContaining(keyword);
        if (posts == null || posts.isEmpty())
            throw new NoSuchElementException("Post not found with content" + keyword);
        Set<PostDto> postDtos = new HashSet<>();
        for (Post post : posts) {
            PostDto postDto = mapper.convertPostToDto(post);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PostDto> getPostsByHashtagId(Long hashtagId) {
        List<Long> postIds = postRepository.findPostIdsByHashtagId(hashtagId);
        Set<PostDto> postDtos = new HashSet<>();
        for (Long postId : postIds) {
            Post post = postRepository.findWithUserAndHashtagsById(postId).get();
            PostDto postDto = mapper.convertPostToDto(post);
            postDtos.add(postDto);
        }
        return postDtos;
    }
    /*
    If we want to use @Transactional(readOnly = true), please be sure that ou import @Transactional from org.springframework.transaction.annotation.Transactional and not from javax.transaction.Transactional as readOnly attribute is specific to Spring transaction.
     */

}
