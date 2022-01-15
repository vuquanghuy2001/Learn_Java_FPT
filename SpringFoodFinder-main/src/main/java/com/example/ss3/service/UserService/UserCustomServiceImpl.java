package com.example.ss3.service.UserService;

import com.example.ss3.dto.UserDto;
import com.example.ss3.entity.UserEntity;
import com.example.ss3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCustomServiceImpl implements UserCustomService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserEntity save(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()) , userDto.getEmail(), 2);
        return userRepo.save(userEntity);
    }

    @Override
    public long getTotal() {
        return userRepo.count();
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        UserEntity userEntity = userRepo.findByUsername(username);
        return userEntity.getId();
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepo.findById(id).get();
    }

    @Override
    public UserEntity getUserByName(String name) {
        return userRepo.findByUsername(name);
    }
}
