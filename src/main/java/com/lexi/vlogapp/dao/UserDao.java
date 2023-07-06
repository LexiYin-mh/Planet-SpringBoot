package com.lexi.vlogapp.dao;

import com.lexi.vlogapp.entity.User;

import java.util.Set;

public interface UserDao extends GenericDao<User, Long> {

    Set<User> fingUsersByName(String name);
}
