package com.digicert.usermanagement.controller;

import com.digicert.usermanagement.domain.model.UserMdl;
import com.digicert.usermanagement.exception.UserNotFoundException;
import com.digicert.usermanagement.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() throws ParseException {
        List<UserMdl> expectedUsers = new ArrayList<>();
        expectedUsers.add(new UserMdl("John", "Doe", "john.doe@test.com"));
        expectedUsers.add(new UserMdl("Jane", "Smith", "john.smith@test.com"));
        when(userService.getAllUsers()).thenReturn(expectedUsers);
        List<UserMdl> actualUsers = userController.getAllUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testGetAllUsersNoRecords() throws ParseException {
        List<UserMdl> expectedUsers = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(expectedUsers);
        List<UserMdl> actualUsers = userController.getAllUsers();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testGetUserById() throws UserNotFoundException, ParseException {
        Long id = 1L;
        UserMdl user = new UserMdl("John", "Doe", "john.doe@test.com");
        when(userService.getUserById(id)).thenReturn(user);
        UserMdl result = userController.getUserById(id);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserByIdThrowsException() throws UserNotFoundException, ParseException {
        Long id = 1L;
        doThrow(new UserNotFoundException("User not found")).when(userService).getUserById(id);
        assertThrows(UserNotFoundException.class, () -> {
            userController.getUserById(id);
        });
    }

}
