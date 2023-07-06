package com.lexi.vlogapp.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


    @Entity
    @Table(name = "posts")
    public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "post_content")
        private String postContent;

        @Column(name = "post_media")
        private String postMedia;

        @Column(name = "post_date")
        private Timestamp postDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id") //, insertable = false, updatable = false)
        private User user;

        @ManyToMany
        @JoinTable(name = "post_hashtag", joinColumns = {@JoinColumn(name = "post_id")},
                inverseJoinColumns = {@JoinColumn(name = "hashtag_id")})
        private Set<Hashtag> hashtags;

        @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
        private Set<Like> likes;

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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }


        public Set<Like> getLikes() {
            return likes;
        }

        public void setLikes(Set<Like> likes) {
            this.likes = likes;
        }

        ///////////////////////////////////////////////////////
        @Override
        public String toString() {
            return "Post{" +
                    "id=" + id +
                    ", postContent='" + postContent + '\'' +
                    ", postMedia='" + postMedia + '\'' +
                    ", postDate=" + postDate +
                    ", user=" + user +
                    '}';
        }

    }
