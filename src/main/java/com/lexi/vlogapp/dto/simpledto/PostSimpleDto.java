package com.lexi.vlogapp.dto.simpledto;

import java.sql.Timestamp;
import java.util.Set;

public class PostSimpleDto {

    private Long id;

    private String postContent;

    private String postMedia;

    private Timestamp postDate;

    private UserSimpleDto userDto;

    private Set<HashtagSimpleDto> hashtagDtos;

    ////////////////////////////Getter & Setter////////////////////////////////////

    

}
