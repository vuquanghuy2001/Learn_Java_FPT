package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Integer id;


    private Integer quantity;


    private Integer product_id;


    private Integer cart_id;

    public ItemDto(Integer quantity, Integer product_id, Integer cart_id) {
        this.quantity = quantity;
        this.product_id = product_id;
        this.cart_id = cart_id;
    }

    public ItemDto(Integer quantity, Integer product_id) {
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public ItemDto(Integer product_id) {
        this.product_id = product_id;
    }
}
