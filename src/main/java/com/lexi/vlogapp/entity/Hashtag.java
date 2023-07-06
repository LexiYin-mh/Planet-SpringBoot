package com.lexi.vlogapp.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hashtags")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hashtag_content")
    private String hashtagContent;

    @ManyToMany(mappedBy = "hashtags", fetch = FetchType.LAZY)
    private Set<Post> posts;

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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    ////////////////////////// To String /////////////////////////////

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", hashtagContent='" + hashtagContent + '\'' +
                '}';
    }

}

