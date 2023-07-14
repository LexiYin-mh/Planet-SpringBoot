package com.lexi.vlogapp.dto;

import java.sql.Timestamp;
import java.util.Set;

public class PostDto {

    private Long id;

    private String postContent;

    private String postMedia;

    private Timestamp postDate;

    private Long likeNum;

    private UserDto user;

    private Set<Long> hashtagIds;

    ////////////////////////////Getter & Setter////////////////////////////////////


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostMedia() {
        return postMedia;
    }

    public void setPostMedia(String postMedia) {
        this.postMedia = postMedia;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<Long> getHashtagIds() {
        return hashtagIds;
    }

    public void setHashtagIds(Set<Long> hashtagIds) {
        this.hashtagIds = hashtagIds;
    }

    //////////////////////////// To String ///////////////////////////////////////


    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", postContent='" + postContent + '\'' +
                ", postMedia='" + postMedia + '\'' +
                ", postDate=" + postDate +
                ", likeNum=" + likeNum +
                '}';
    }

}
