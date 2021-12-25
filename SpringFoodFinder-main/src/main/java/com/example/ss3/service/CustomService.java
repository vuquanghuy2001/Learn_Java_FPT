package com.example.ss3.service;

import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.UserEntity;
import org.springframework.security.core.Authentication;

public interface CustomService {
    Integer getUserId();
    String filterDish(DishEntity dishEntity);
}


