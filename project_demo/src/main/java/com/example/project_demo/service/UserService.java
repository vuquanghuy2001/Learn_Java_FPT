package com.example.project_demo.service;

import com.example.project_demo.web.dto.UserRegistrationDto;
import com.example.project_demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
