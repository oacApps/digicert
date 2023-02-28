package com.digicert.usermanagement.service;

import com.digicert.usermanagement.domain.entity.UserEntity;
import com.digicert.usermanagement.domain.mapper.UserMapper;
import com.digicert.usermanagement.domain.model.UserMdl;
import com.digicert.usermanagement.exception.UserNotFoundException;
import com.digicert.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserMdl createUser(UserMdl userMdl) throws ParseException {
        UserEntity userEnt = null;
        userEnt = UserMapper.INSTANCE.toEntity(userMdl);
        userEnt = userRepository.save(userEnt);
        userMdl = UserMapper.INSTANCE.toModel(userEnt);
        return userMdl;
    }

    public UserMdl updateUser(Long id, UserMdl userMdl) throws ParseException, UserNotFoundException {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        existingUser = UserMapper.INSTANCE.toEntity(userMdl);
        existingUser = userRepository.save(existingUser);
        return UserMapper.INSTANCE.toModel(existingUser);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.delete(existingUser);
    }

    public List<UserMdl> getAllUsers() throws ParseException {
        return UserMapper.INSTANCE.toModelList(userRepository.findAll());
    }

    public UserMdl getUserById(Long id) throws UserNotFoundException, ParseException {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return UserMapper.INSTANCE.toModel(existingUser);
    }
}
