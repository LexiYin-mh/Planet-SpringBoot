package com.lexi.vlogapp.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name = "likes")
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "like_date")
    private Timestamp likeDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
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

    // toString method
    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", likeDate=" + likeDate +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}

