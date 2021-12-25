package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;

    private String name;

    private String image;

    public CategoryDto(String name,String image) {
        this.name = name;
        this.image = image;
    }
}
