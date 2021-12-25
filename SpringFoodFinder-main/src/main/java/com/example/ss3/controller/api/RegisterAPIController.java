package com.example.ss3.controller.api;

import com.example.ss3.dto.CartDto;
import com.example.ss3.dto.ItemDto;
import com.example.ss3.dto.UserDto;
import com.example.ss3.entity.UserEntity;
import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.CartService;
import com.example.ss3.service.UserCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/register")
public class RegisterAPIController {
    @Autowired
    private UserCustomService userCustomService;
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity create(@RequestBody UserDto userDto){
        BaseResponse res = new BaseResponse();
        UserEntity userEntity = userCustomService.save(userDto);
        res.data = userEntity;
        cartService.add(new CartDto(userEntity.getId()));
        return  ResponseEntity.ok(res);
    }
}
