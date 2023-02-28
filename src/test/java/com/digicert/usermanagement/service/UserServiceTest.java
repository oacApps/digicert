package com.digicert.usermanagement.service;

import com.digicert.usermanagement.domain.entity.UserEntity;
import com.digicert.usermanagement.domain.mapper.UserMapper;
import com.digicert.usermanagement.domain.model.UserMdl;
import com.digicert.usermanagement.exception.UserNotFoundException;
import com.digicert.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        userService.deleteUser(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(userEntity);
    }

    @Test
    public void testDeleteUserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).delete(any(UserEntity.class));
    }


}
