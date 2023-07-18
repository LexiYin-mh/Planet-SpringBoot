package com.lexi.vlogapp.dto;

import com.lexi.vlogapp.entity.User;

public class LoginResult {
    private boolean success;
    private UserDto userDto;

    public LoginResult(boolean success, UserDto userDto) {
        this.success = success;
        this.userDto = userDto;
    }

    public boolean isSuccess() {
        return success;
    }

    public UserDto getUserDto() {
        return this.userDto;
    }

}
