package com.lexi.vlogapp.Service.impl;

import com.lexi.vlogapp.Service.UserService;
import com.lexi.vlogapp.dao.repository.RoleRepository;
import com.lexi.vlogapp.dao.repository.UserRepository;
import com.lexi.vlogapp.dto.UserDto;
import com.lexi.vlogapp.dto.LoginResult;
import com.lexi.vlogapp.entity.Role;
import com.lexi.vlogapp.entity.User;
import com.lexi.vlogapp.exception.InvalidCredentialsException;
import com.lexi.vlogapp.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public UserDto getById(Long id) {
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

    /*
    This getAllUsers() method is only used for Mockito test using mock with iterator, and it would not be used in practical project.
     */
    @Override
    public Set<UserDto> getAllUsers() {

        Set<User> users = userRepository.findAllUsers();

        Set<UserDto> userDtos = new HashSet<>();
        for (User user : users) {
            UserDto userDto = mapper.convertUserToDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public Set<UserDto> getByName(String name) {
        Set<User> users = userRepository.findByName(name);
        Set<UserDto> userDtos = new HashSet<>();
        for (User user : users) {
            UserDto userDto = mapper.convertUserToDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto getUserWithRolesByCredential(String userEmail, String userPassword) {

        // verify user Login and get user
        LoginResult loginResult = verifyUserLogin(userEmail, userPassword);

        if (!loginResult.isSuccess())
            throw new InvalidCredentialsException("UserEmail and Password are not matched.");

        return loginResult.getUserDto();
    }

    @Override
    public LoginResult verifyUserLogin(String userEmail, String userPassword) {
        Boolean flag = false;
        UserDto userDto = new UserDto();
        Optional<User> userOpt = userRepository.findByEmail(userEmail);
        if(userOpt.isPresent()) {
            userDto = mapper.convertUserToDto(userOpt.get());
            flag = true;
        }
        return new LoginResult(flag, userDto);
    }



}
