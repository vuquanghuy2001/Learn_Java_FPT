package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    private Integer id;

    @NotNull
    private String name;

    public StatusDto(@NotNull String name) {
        this.name = name;
    }
}
