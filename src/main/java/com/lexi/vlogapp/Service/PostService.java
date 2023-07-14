package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.entity.Post;

import java.util.Set;

public interface PostService extends CrudService<Post, Long> {

    Set<Post> getUserRecentPosts(Long userId, int limit);

    Set<Post> getPopularPosts(int limit);

    Post getPostAndUserAndHashtags(Long postId);

    //Post getPostAndUserAndHashtags(Post post);

}
