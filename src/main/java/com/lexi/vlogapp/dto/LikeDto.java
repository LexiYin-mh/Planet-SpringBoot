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
}
