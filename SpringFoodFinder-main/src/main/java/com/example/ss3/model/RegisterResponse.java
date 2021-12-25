package com.example.ss3.model;

import com.example.ss3.entity.UserEntity;

public class RegisterResponse {
    private  Integer userId;
    private UserEntity userEntity;


    public RegisterResponse(Integer userId, UserEntity userEntity) {
        this.userId = userId;
        this.userEntity = userEntity;
    }

    public RegisterResponse() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
