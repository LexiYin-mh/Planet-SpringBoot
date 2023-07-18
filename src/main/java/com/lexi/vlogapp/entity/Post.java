package com.lexi.vlogapp.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "user_id") //, insertable = false, updatable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "post_hashtag", joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "hashtag_id")})
    private Set<Hashtag> hashtags;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Like> likes;

    @Column(name = "post_beliked", columnDefinition = "bigint default 0", insertable = false)
    private Long likeNum;

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
        user.getPosts().add(this);
    }

    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }


    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public void removeHashtag(Hashtag hashtag) {
        this.getHashtags().remove(hashtag);
        hashtag.getPosts().remove(this);
    }

    //////////////////////////// To String ///////////////////////////////////////

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postContent='" + postContent + '\'' +
                ", postMedia='" + postMedia + '\'' +
                ", postDate=" + postDate +
                ", likeNum=" + likeNum +
                '}';
    }


//    @Override
//    public String toString() {
//        return "Post{" +
//                "id=" + id +
//                ", postContent='" + postContent + '\'' +
//                ", postMedia='" + postMedia + '\'' +
//                ", postDate=" + postDate +
//                ", user=" + user +
//                ", hashtags=" + hashtags +
//                ", likes=" + likes +
//                ", likeNum=" + likeNum +
//                '}';
//    }
}
