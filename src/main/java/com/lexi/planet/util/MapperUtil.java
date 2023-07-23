package com.lexi.planet.util;

import com.lexi.planet.dto.*;
import com.lexi.planet.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MapperUtil {

    public UserDto convertUserToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setAvatar(user.getAvatar());
        userDto.setLikeNum(user.getLikeNum());

        if (user.getRoles() == null) {
            Set<RoleDto> roleDtos = new HashSet<>();
        }
        Set<RoleDto> roleDtos = convertRoleSetToDtoSet(user.getRoles());
        userDto.setRoleDtos(roleDtos);
        return userDto;
    }

    public User convertUserDtoToEntity(UserDto userDto) {

        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAvatar(userDto.getAvatar());
        user.setLikeNum(userDto.getLikeNum());

        if (userDto.getRoleDtos() == null) {
            Set<Role> roles = new HashSet<>();
        }
        Set<Role> roles = convertRoleDtoSetToEntitySet(userDto.getRoleDtos());
        user.setRoles(roles);
        return user;
    }


    private Set<RoleDto> convertRoleSetToDtoSet(Set<Role> roles) {

        Set<RoleDto> roleDtoSet = new HashSet<>();

        for (Role role : roles) {
            RoleDto roleDto = convertRoleToDto(role);
            roleDtoSet.add(roleDto);
        }

        return roleDtoSet;
    }

    public RoleDto convertRoleToDto(Role role) {

        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setAllowedResources(role.getAllowedResources());
        roleDto.setAllowedCreate(role.isAllowedCreateFlag());
        roleDto.setAllowedRead(role.isAllowedReadFlag());
        roleDto.setAllowedUpdate(role.isAllowedUpdateFlag());
        roleDto.setAllowedDelete(role.isAllowedDeleteFlag());

        return roleDto;
    }

    private Set<Role> convertRoleDtoSetToEntitySet(Set<RoleDto> roleDtoSet) {

        Set<Role> roles = new HashSet<>();

        for (RoleDto roleDto : roleDtoSet) {
            Role role = convertRoleDtoToEntity(roleDto);
            roles.add(role);
        }
        return roles;
    }

    private Role convertRoleDtoToEntity(RoleDto roleDto) {

        Role role = new Role();

        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setAllowedResources(roleDto.getAllowedResources());
        role.setAllowedCreateFlag(roleDto.isAllowedCreate());
        role.setAllowedReadFlag(roleDto.isAllowedRead());
        role.setAllowedUpdateFlag(roleDto.isAllowedUpdate());
        role.setAllowedDeleteFlag(roleDto.isAllowedDelete());

        return role;
    }

    public PostDto convertPostToDto(Post post) {

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setPostContent(post.getPostContent());
        postDto.setPostMedia(post.getPostMedia());
        postDto.setPostDate(post.getPostDate());
        postDto.setLikeNum(post.getLikeNum());

        UserDto userDto = convertUserToDto(post.getUser());
        postDto.setUserDto(userDto);

        if (post.getHashtags() == null) {
            Set<HashtagDto> hashtagDtos = new HashSet<>();
        }

        Set<HashtagDto> hashtagDtos = convertHashtagSetToHashtagDtoSet(post.getHashtags());
        postDto.setHashtagDtos(hashtagDtos);

        return postDto;
    }

    private Set<HashtagDto> convertHashtagSetToHashtagDtoSet(Set<Hashtag> hashtags) {

        Set<HashtagDto> hashtagDtos = new HashSet<>();

        for (Hashtag hashtag : hashtags) {
            HashtagDto hashtagDto = convertHashtagToDto(hashtag);
            hashtagDtos.add(hashtagDto);
        }

        return hashtagDtos;
    }

    public Post convertPostDtoToEntity(PostDto postDto) {

        Post post = new Post();

        post.setId(postDto.getId());
        post.setPostContent(postDto.getPostContent());
        post.setPostMedia(postDto.getPostMedia());
        post.setPostDate(postDto.getPostDate());
        post.setLikeNum(postDto.getLikeNum());

        User user = convertUserDtoToEntity(postDto.getUserDto());
        post.setUser(user);

        if (postDto.getHashtagDtos() != null) {
            Set<Hashtag> hashtags = new HashSet<>();
        }
        Set<Hashtag> hashtags = convertHashtagDtoSetToEntitySet(postDto.getHashtagDtos());
        post.setHashtags(hashtags);

        return post;
    }

    private Set<Hashtag> convertHashtagDtoSetToEntitySet(Set<HashtagDto> hashtagDtos) {

        Set<Hashtag> hashtags = new HashSet<>();

        for (HashtagDto hashtagDto : hashtagDtos) {
            Hashtag hashtag = convertHashtagDtoToEntity(hashtagDto);
            hashtags.add(hashtag);
        }

        return hashtags;
    }


    public HashtagDto convertHashtagToDto(Hashtag hashtag) {

        HashtagDto hashtagDto = new HashtagDto();

        hashtagDto.setId(hashtag.getId());
        hashtagDto.setHashtagContent(hashtag.getHashtagContent());

        return hashtagDto;
    }

    public Hashtag convertHashtagDtoToEntity(HashtagDto hashtagDto) {

        Hashtag hashtag = new Hashtag();

        hashtag.setId(hashtagDto.getId());
        hashtag.setHashtagContent(hashtagDto.getHashtagContent());

        return hashtag;

    }

    public LikeDto convertLikeToDto(Like like) {

        LikeDto likeDto = new LikeDto();

        likeDto.setId(like.getId());
        likeDto.setLikeDate(like.getLikeDate());
        likeDto.setUser(like.getUser());
        likeDto.setPost(like.getPost());

        return likeDto;
    }

    public Like convertLikeDtoToEntity(LikeDto likeDto) {

        Like like = new Like();

        like.setId(likeDto.getId());
        like.setLikeDate(likeDto.getLikeDate());
        like.setUser(likeDto.getUser());
        like.setPost(likeDto.getPost());

        return like;
    }






}
