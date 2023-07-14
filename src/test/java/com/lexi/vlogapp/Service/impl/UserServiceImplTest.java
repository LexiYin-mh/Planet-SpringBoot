package com.lexi.vlogapp.Service.impl;

import com.lexi.vlogapp.Service.UserService;
import com.lexi.vlogapp.dao.repository.RoleRepository;
import com.lexi.vlogapp.dao.repository.UserRepository;
import com.lexi.vlogapp.dto.UserDto;
import com.lexi.vlogapp.entity.Role;
import com.lexi.vlogapp.entity.User;
import com.lexi.vlogapp.util.MapperUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository mockedUserRepository;

    @MockBean
    private RoleRepository mockedRoleRepository;

    @MockBean
    private MapperUtil mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createTest_happy_path() {
        // create mock data
        UserDto mockedUserDto =  mock(UserDto.class);
        User mockedUser = mock(User.class);

        Role defaultRole = new Role();
        defaultRole.setName("User");

        User mockedSavedUser = mock(User.class);
        UserDto returnedUserDto = mock(UserDto.class);

        // Set behavior of mocks
        when(mapper.convertUserDtoToEntity(mockedUserDto)).thenReturn(mockedUser);
        when(mockedUser.getEmail()).thenReturn("test@test.com");
        when(mockedUser.getPassword()).thenReturn("test password");
        when(mockedRoleRepository.findRoleByName("User")).thenReturn(Optional.of(defaultRole));
        when(mockedUserRepository.save(mockedUser)).thenReturn(mockedSavedUser);
        when(mapper.convertUserToDto(mockedSavedUser)).thenReturn(returnedUserDto);

        // Call method under test
        UserDto result = userService.create(mockedUserDto);

        // Verify interactions
        verify(mockedUserRepository, times(1)).save(mockedUser);

        assertEquals(returnedUserDto, result);
    }

    @Test
    void createTest_failed() throws Exception{
        // create mock data
        UserDto mockedUserDto =  mock(UserDto.class);
        User mockedUser = mock(User.class);

        Role defaultRole = new Role();
        defaultRole.setName("User");

        User mockedSavedUser = mock(User.class);
        UserDto returnedUserDto = mock(UserDto.class);

        // Set behavior of mocks
        when(mapper.convertUserDtoToEntity(mockedUserDto)).thenReturn(mockedUser);
        //when(mockedUser.getEmail()).thenReturn("");
        when(mockedUser.getEmail()).thenReturn(null);
        when(mockedUser.getPassword()).thenReturn("test password");

        // Call method under test and verify interaction
        assertThrows(IllegalArgumentException.class, () -> {
            userService.create(mockedUserDto);
        });

        verify(mockedUserRepository, never()).save(any());

    }

    @Test
    void updateTest_happy_path() {
        // create mock data
        UserDto mockedUserDto =  mock(UserDto.class);
        User mockedUser = mock(User.class);

        User mockedUpdateddUser = mock(User.class);
        UserDto returnedUserDto = mock(UserDto.class);

        // Set behavior of mocks
        when(mapper.convertUserDtoToEntity(mockedUserDto)).thenReturn(mockedUser);
        when(mockedUser.getId()).thenReturn(5L);
        when(mockedUserRepository.existsById(anyLong())).thenReturn(true);
        when(mockedUserRepository.save(mockedUser)).thenReturn(mockedUpdateddUser);
        when(mapper.convertUserToDto(mockedUpdateddUser)).thenReturn(returnedUserDto);

        // Call method under test
        UserDto result = userService.update(mockedUserDto);

        // Verify interactions
        verify(mockedUserRepository, times(1)).save(mockedUser);

        assertEquals(returnedUserDto, result);
    }

    @Test
    void deleteTest() {
        // create mock data
        UserDto mockedUserDto =  mock(UserDto.class);
        User mockedUser = mock(User.class);

        // Set behavior of mocks
        when(mapper.convertUserDtoToEntity(mockedUserDto)).thenReturn(mockedUser);
        when(mockedUserRepository.existsById(anyLong())).thenReturn(true);

        // Call method under test
        Boolean result = userService.delete(mockedUserDto);

        // Verify interactions
        verify(mockedUserRepository, times(1)).existsById(anyLong());

        assertTrue(result);
    }

    @Test
    void deleteByIdTest() {
        // create mock data
        UserDto mockedUserDto =  mock(UserDto.class);
        User mockedUser = mock(User.class);

        // Set behavior of mocks
        when(mapper.convertUserDtoToEntity(mockedUserDto)).thenReturn(mockedUser);
        when(mockedUserRepository.existsById(anyLong())).thenReturn(true);

        // Call method under test
        Boolean result = userService.deleteById(10L);

        // Verify interactions
        verify(mockedUserRepository, times(1)).existsById(anyLong());
        verify(mockedUserRepository, times(1)).deleteById(anyLong());

        assertTrue(result);
    }



}