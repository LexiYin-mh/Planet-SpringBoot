package com.lexi.vlogapp.util;

import com.lexi.vlogapp.dto.PostDto;
import com.lexi.vlogapp.dto.RoleDto;
import com.lexi.vlogapp.dto.UserDto;
import com.lexi.vlogapp.entity.Post;
import com.lexi.vlogapp.entity.Role;
import com.lexi.vlogapp.entity.User;
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

        if (user.getRoles() != null) {
            Set<RoleDto> roleDtos = convertRoleSetToDtoSet(user.getRoles());
            userDto.setRoleDtos(roleDtos);
        }

        return userDto;
    }

    public User convertUserDtoToEntity(UserDto userDto) {

        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAvatar(userDto.getAvatar());
        user.setLikeNum(userDto.getLikeNum());

        if (userDto.getRoleDtos() != null) {
            Set<Role> roles = convertRoleDtoSetToEntitySet(userDto.getRoleDtos());
            user.setRoles(roles);
        }

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

        return role;
    }
}
