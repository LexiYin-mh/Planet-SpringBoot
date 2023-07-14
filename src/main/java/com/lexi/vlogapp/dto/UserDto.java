package com.lexi.vlogapp.dto;

import com.lexi.vlogapp.entity.Role;

import java.util.Set;

public class UserDto {

    private Long id;

    private String email;

    private String name;

    private String avatar;

    private Long likeNum;

    private Set<RoleDto> roleDtos;

    ///////////////////////////// Getter & Setter //////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Set<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(Set<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }

    //////////////////////////// To String ///////////////////////////////////////
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", likeNum=" + likeNum +
                '}';
    }

}
