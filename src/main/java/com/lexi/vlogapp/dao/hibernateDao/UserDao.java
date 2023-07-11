package com.lexi.vlogapp.dao.hibernateDao;

import com.lexi.vlogapp.entity.User;

import java.util.Set;

public interface UserDao extends GenericDao<User, Long> {

    Set<User> findUsersByName(String name);

    Set<User> findUserByEmail(String email);
}
