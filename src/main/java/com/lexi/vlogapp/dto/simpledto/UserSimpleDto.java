package com.lexi.vlogapp.dto.simpledto;

import java.util.Set;

public class UserSimpleDto {

    private Long id;

    private String email;

    private String name;

    private String password;

    private String avatar;

    /*
    * In some scenario, including PostDto inside UserDto can indeed be useful, especially when you want to retrieve a user and all of their posts in a single operation.
    * In a more complex system, you might end up with several versions of UserDto for different purposes: a UserDto that includes posts; another without posts.
    * If including post, we must handle the circular reference properly.
    */
    //private Set<PostDto> posts;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvater(String avatar) {
        this.avatar = avatar;
    }

    public Set<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(Set<RoleDto> roleSimpleDtos) {
        this.roleDtos = roleSimpleDtos;
    }


    //////////////////////////// To String ///////////////////////////////////////

    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

