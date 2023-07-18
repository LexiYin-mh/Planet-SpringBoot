package com.lexi.vlogapp.dto;

import java.util.Set;

public class HashtagDto {

    private Long id;

    private String hashtagContent;

    private Set<Long> postIds;

    //////////////////// Getter & Setter /////////////////////////////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashtagContent() {
        return hashtagContent;
    }

    public void setHashtagContent(String hashtagContent) {
        this.hashtagContent = hashtagContent;
    }

    public Set<Long> getPostIds() {
        return postIds;
    }

    public void setPostIds(Set<Long> postIds) {
        this.postIds = postIds;
    }

    ////////////////////////// To String /////////////////////////////

    @Override
    public String toString() {
        return "HashtagDto{" +
                "id=" + id +
                ", hashtagContent='" + hashtagContent + '\'' +
                '}';
    }


}
