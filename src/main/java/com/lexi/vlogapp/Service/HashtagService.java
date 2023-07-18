package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dto.HashtagDto;
import com.lexi.vlogapp.dto.PostDto;


import java.util.Set;

public interface HashtagService extends CrudService<HashtagDto, Long> {

    Boolean deleteByHashtagContent(String hashtagContent);

    HashtagDto getByHashtagContent(String hashtagContent);



}
