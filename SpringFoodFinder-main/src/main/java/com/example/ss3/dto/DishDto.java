package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private Integer id;

    private String name;

    private Integer category_id;

    private Integer recipe_id;

    private String image_url;

    private String method;

    private String ingredient_des;

    private  float price;
}
