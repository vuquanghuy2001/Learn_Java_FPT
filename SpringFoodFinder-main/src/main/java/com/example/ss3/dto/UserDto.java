package com.example.ss3.dto;

import com.example.ss3.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String username;
    private String password;
    private String email;
    private Integer roleid;

    public UserDto(String username, String password, String email, Integer roleid) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleid = roleid;
    }
}
