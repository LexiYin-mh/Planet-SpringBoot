package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.entity.Hashtag;
import com.lexi.vlogapp.entity.Post;

import java.util.Set;

public interface HashtagService extends CrudService<Hashtag, Long> {

    Set<Post> getHashtagPosts(Long hashtagId);

}
