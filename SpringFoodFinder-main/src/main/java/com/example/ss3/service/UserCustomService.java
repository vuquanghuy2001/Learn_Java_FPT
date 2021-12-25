package com.example.ss3.service;

import com.example.ss3.dto.UserDto;
import com.example.ss3.entity.UserEntity;
import org.springframework.security.core.userdetails.User;


public interface UserCustomService {
    UserEntity save(UserDto userDto);
    long getTotal();
    Integer getUserIdByUsername(String username);
    UserEntity getUserById(Integer id);
    UserEntity getUserByName(String name);
}
