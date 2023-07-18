package com.lexi.vlogapp.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

    private Long id;

    private String postContent;

    private String postMedia;

    private Timestamp postDate;

    private Long likeNum;

    private UserDto userDto;

    private Set<HashtagDto> hashtagDtos = new HashSet<>();

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto user) {
        this.userDto = user;
    }

    public Set<HashtagDto> getHashtagDtos() {
        return hashtagDtos;
    }

    public void setHashtagDtos(Set<HashtagDto> hashtagDtos) {
        this.hashtagDtos = hashtagDtos;
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


//    @Override
//    public String toString() {
//        return "PostDto{" +
//                "id=" + id +
//                ", postContent='" + postContent + '\'' +
//                ", postMedia='" + postMedia + '\'' +
//                ", postDate=" + postDate +
//                ", likeNum=" + likeNum +
//                ", userDto=" + userDto +
//                ", hashtagDtos=" + hashtagDtos +
//                '}';
//    }
}
