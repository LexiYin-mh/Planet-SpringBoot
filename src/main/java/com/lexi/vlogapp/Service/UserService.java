package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dao.UserDao;
import com.lexi.vlogapp.entity.User;
import com.lexi.vlogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> retrievedUser = userRepository.findById(id);
        return Optional.empty();
    }

    public Set<User> findAll() {
        Set<User> users = new HashSet<>(userRepository.findAll());
        return users;
    }

    @Override
    public Set<User> fingUsersByName(String name) {
        return null;
    }

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User update(User model) {
        return null;
    }

    @Override
    public boolean delete(User model) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }


}
