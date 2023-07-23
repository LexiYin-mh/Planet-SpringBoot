package com.lexi.planet.Service;

import com.lexi.planet.dto.UserDto;
import com.lexi.planet.dto.LoginResult;

import java.util.Set;

public interface UserService extends CrudService<UserDto, Long>{

    Set<UserDto> getByName(String name);

    Set<UserDto> getAllUsers();

    UserDto getUserWithRolesByCredential(String userEmail, String userPassword);

    LoginResult verifyUserLogin(String userEmail, String userPassword);

}
