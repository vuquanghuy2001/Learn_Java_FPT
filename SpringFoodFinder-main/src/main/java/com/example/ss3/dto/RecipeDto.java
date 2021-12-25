package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    private Integer id;

    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    @Size(min = 3, max = 10000, message = "Description must be at least 3 character")
    private String description;

    @NotNull
    private Integer category_id;

    public RecipeDto(String name, String description, Integer category_id) {
        this.name = name;
        this.description = description;
        this.category_id = category_id;
    }
}
