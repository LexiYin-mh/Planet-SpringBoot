package com.lexi.vlogapp.dto;

import com.lexi.vlogapp.entity.Post;
import com.lexi.vlogapp.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

public class LikeDto {

    private Long id;

    private Timestamp likeDate;

    private User user;

    private Post post;

    /////////////////////////// Getters and Setters ///////////////////////////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Timestamp likeDate) {
        this.likeDate = likeDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
