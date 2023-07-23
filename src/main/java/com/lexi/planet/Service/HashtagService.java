package com.lexi.planet.Service;

import com.lexi.planet.dto.HashtagDto;

public interface HashtagService extends CrudService<HashtagDto, Long> {

    Boolean deleteByHashtagContent(String hashtagContent);

    HashtagDto getByHashtagContent(String hashtagContent);



}
