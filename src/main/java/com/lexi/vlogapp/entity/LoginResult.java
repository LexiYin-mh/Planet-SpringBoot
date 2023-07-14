package com.lexi.vlogapp.entity;

public class LoginResult {
    private boolean success;
    private User user;

    public LoginResult(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

}
