package com.lexi.planet.Service.impl;

import com.lexi.planet.Service.HashtagService;
import com.lexi.planet.dao.repository.HashtagRepository;
import com.lexi.planet.dto.HashtagDto;
import com.lexi.planet.entity.Hashtag;
import com.lexi.planet.entity.Post;
import com.lexi.planet.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class HashtagServiceImpl implements HashtagService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private MapperUtil mapper;


    @Override
    public HashtagDto create(HashtagDto hashtagDto) {
        // Check if hashtag_content has existed
        Boolean isHashtagExist = hashtagRepository.existsByHashtagContent(hashtagDto.getHashtagContent());
        if (isHashtagExist) {
            logger.warn("====== Hashtag " + hashtagDto.getHashtagContent() + "has already exist.");
            return hashtagDto;
        }

        // Check if hashtag content is sensitive
        validateContent(hashtagDto);

        // If it does not exist, then create a new one
        Hashtag hashtag = mapper.convertHashtagDtoToEntity(hashtagDto);
        Hashtag savedHashtag = hashtagRepository.save(hashtag);
        HashtagDto savedHashtagDto = mapper.convertHashtagToDto(savedHashtag);
        return savedHashtagDto;
    }


    @Override
    public HashtagDto update(HashtagDto hashtagDto) {

        // Check if hashtag content is sensitive
        validateContent(hashtagDto);

        Hashtag hashtag = mapper.convertHashtagDtoToEntity(hashtagDto);
        Hashtag updatedHashtag = hashtagRepository.save(hashtag);
        HashtagDto updatedHashtagDto = mapper.convertHashtagToDto(updatedHashtag);
        return updatedHashtagDto;
    }

    private void validateContent(HashtagDto hashtagDto) {
        // If contains sensitive content, throw exception
    }

    @Override
    public Boolean delete(HashtagDto hashtagDto) {
        Hashtag hashtag = mapper.convertHashtagDtoToEntity(hashtagDto);
        if (hashtag.getId() == null || hashtagRepository.existsById(hashtag.getId())) {
            logger.error("========== Hashtag does not exist!");
        }
        hashtagRepository.delete(hashtag);
        Boolean flag = true;
        return flag;
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        Hashtag hashtag = hashtagRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hashtag with id " + id + " not found"));
        for (Post post : hashtag.getPosts()) {
            post.removeHashtag(hashtag);
        }
        hashtagRepository.delete(hashtag);
        Boolean flag = true;
        return flag;
    }

    @Override
    @Transactional
    public Boolean deleteByHashtagContent(String hashtagContent) {
        Hashtag hashtag = hashtagRepository.findByHashtagContent(hashtagContent)
                .orElseThrow(() -> new NoSuchElementException("Hashtag " + hashtagContent + " not found"));
        for (Post post : hashtag.getPosts()) {
            post.removeHashtag(hashtag);
        }
        hashtagRepository.delete(hashtag);
        Boolean flag = true;
        return flag;
    }

    @Override
    public HashtagDto getById(Long id) {
        Hashtag hashtag = hashtagRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hashtag with id " + id + " not found"));
        HashtagDto retrievedHashtag = mapper.convertHashtagToDto(hashtag);
        return retrievedHashtag;
    }

    @Override
    public HashtagDto getByHashtagContent(String hashtagContent) {
        Hashtag hashtag = hashtagRepository.findByHashtagContent(hashtagContent)
                .orElseThrow(() -> new NoSuchElementException("Hashtag " + hashtagContent + " not found"));
        HashtagDto retrievedHashtag = mapper.convertHashtagToDto(hashtag);
        return retrievedHashtag;
    }

    @Override
    public Set<HashtagDto> getAll() {
        Set<Hashtag> hashtags = new HashSet<>(hashtagRepository.findAll());

        Set<HashtagDto> hashtagDtos = new HashSet<>();

        for (Hashtag hashtag : hashtags) {
            HashtagDto hashtagDto = mapper.convertHashtagToDto(hashtag);
            hashtagDtos.add(hashtagDto);
        }

        return hashtagDtos;
    }

}
