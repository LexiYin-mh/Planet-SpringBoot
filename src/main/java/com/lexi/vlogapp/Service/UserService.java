package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dto.UserDto;
import com.lexi.vlogapp.dto.LoginResult;
import com.lexi.vlogapp.entity.User;

import java.util.Set;

public interface UserService extends CrudService<UserDto, Long>{

    Set<UserDto> getByName(String name);

    Set<UserDto> getAllUsers();

    UserDto getUserWithRolesByCredential(String userEmail, String userPassword);

    LoginResult verifyUserLogin(String userEmail, String userPassword);

}
