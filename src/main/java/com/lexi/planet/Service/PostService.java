package com.lexi.planet.Service;

import com.lexi.planet.dto.PostDto;

import java.util.List;
import java.util.Set;

public interface PostService extends CrudService<PostDto, Long> {

    List<PostDto> getUserRecentPosts(Long userId, int limit);

    List<PostDto> getUserRecentPosts(Long userId); // return default amount of recent posts

    List<PostDto> getPopularPosts(int limit);

    List<PostDto> getPopularPosts();

    PostDto getPostAndUserAndHashtagsByPostId(Long postId);

    //Post getPostAndUserAndHashtags(Post post);

    Set<PostDto> searchPostByContent(String keyword);

    Set<PostDto> getPostsByHashtagId(Long hashtagId);

}
