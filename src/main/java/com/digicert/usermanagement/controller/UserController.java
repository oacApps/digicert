package com.digicert.usermanagement.controller;

import com.digicert.usermanagement.domain.model.UserMdl;
import com.digicert.usermanagement.exception.UserNotFoundException;
import com.digicert.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserMdl> getAllUsers() throws ParseException {
        List<UserMdl> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        }
        return users;
    }

    @GetMapping("/{id}")
    public UserMdl getUserById(@PathVariable @NotNull @Positive Long id) throws UserNotFoundException, ParseException {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserMdl createUser(@RequestBody @Valid UserMdl userMdl) throws ParseException {
        return userService.createUser(userMdl);
    }

    @PutMapping("/{id}")
    public UserMdl updateUser(@PathVariable @NotNull Long id, @RequestBody @Valid UserMdl userMdl) throws ParseException, UserNotFoundException {
        return userService.updateUser(id, userMdl);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NotNull Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
