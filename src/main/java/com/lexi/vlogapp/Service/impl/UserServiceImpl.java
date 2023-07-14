package com.lexi.vlogapp.Service.impl;

import com.lexi.vlogapp.Service.UserService;
import com.lexi.vlogapp.dao.repository.RoleRepository;
import com.lexi.vlogapp.dao.repository.UserRepository;
import com.lexi.vlogapp.dto.UserDto;
import com.lexi.vlogapp.entity.LoginResult;
import com.lexi.vlogapp.entity.Role;
import com.lexi.vlogapp.entity.User;
import com.lexi.vlogapp.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MapperUtil mapper;

    @Override
    public UserDto create(UserDto userDto) {

        User user = mapper.convertUserDtoToEntity(userDto);

        // Verifying user information
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        Role defaultRole = roleRepository.findRoleByName("User").get();

        user.getRoles().add(defaultRole);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = mapper.convertUserToDto(savedUser);

        return savedUserDto;

    }

    @Override
    public UserDto update(UserDto userDto) {

        User user = mapper.convertUserDtoToEntity(userDto);

        // verify if User has valid id
        if (user.getId() == null) {
            throw new IllegalArgumentException("Cannot update user with null ID.");
        }
        // verify if the user entity exist
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist.");
        }

        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = mapper.convertUserToDto(updatedUser);

        return updatedUserDto;
    }

    @Override
    public Boolean delete(UserDto userDto) {

        User user = mapper.convertUserDtoToEntity(userDto);

        Boolean deleteFlag = false;
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist.");
        }

        userRepository.delete(user);
        deleteFlag = true;

        return deleteFlag;
    }

    @Override
    public Boolean deleteById(Long id) {
        Boolean deleteFlag = false;
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
        deleteFlag = true;
        return deleteFlag;
    }

    @Override
    public UserDto getById(Long id) throws NoSuchElementException {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()){
            UserDto userDto = mapper.convertUserToDto(userOpt.get());
            return userDto;
        } else {
            throw new NoSuchElementException("Could not find User with id = " + id);
        }
    }

    @Override
    public Set<UserDto> getAll() {
        Set<User> users = new HashSet<>(userRepository.findAll());
        Set<UserDto> userDtos = new HashSet<>();
        for (User user : users) {
            UserDto userDto = mapper.convertUserToDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

//    @Override
//    public Set<User> getByName(String name) {
//        Set<User> users = userRepository.findByName(name);
//        if (users.isEmpty()) {
//            logger.error("===== There is no user with name = {}", name);
//        }
//        return users;
//    }
//
//    public Role getUserAndRolesByCredential(String userEmail, String userPassword) {
//
//        // verify user Login and get user
//        LoginResult loginResult = verifyUserLogin(userEmail, userPassword);
//        if (loginResult.isSuccess()) {
//            // get user info and
//            User user = loginResult.getUser();
//            Set<Role> roles = user.getRoles();
//        }
//
//        return null;
//    }
//
//    public Set<Role> getRolesById(Long id) {
//        Optional<User> userOpt = userRepository.findById(id);
//        if (userOpt.isPresent()){
//            User user = userOpt.get();
//            Set<Role> roles = user.getRoles();
//            return roles;
//        } else {
//            throw new NoSuchElementException("Could not find User with id = " + id);
//        }
//    }
//
//    @Override
//    public LoginResult verifyUserLogin(String userEmail, String userPassword) {
//        Boolean flag = false;
//        User user = new User();
//        Optional<User> userOpt = userRepository.findByEmail(userEmail);
//        if(userOpt.isPresent()) {
//            user = userOpt.get();
//            flag = true;
//        }
//        return new LoginResult(flag, user);
//    }


}
