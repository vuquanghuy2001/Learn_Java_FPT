package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Integer id;

    @NotNull
    private Integer user_id;

    @NotNull
    private String phone;


    public Collection<ItemDto> items;

    @NotNull
    private String address;


    private String comment;


    private Integer status_id = 1;

    public CartDto(@NotNull Integer user_id,  @NotNull String phone, @NotNull String address, String comment, Integer status_id) {
        this.user_id = user_id;
        this.phone = phone;
        this.address = address;
        this.comment = comment;
        this.status_id = status_id;
    }

    public CartDto(@NotNull Integer user_id, @NotNull String phone, Collection<ItemDto> items, @NotNull String address, String comment, Integer status_id) {
        this.user_id = user_id;
        this.phone = phone;
        this.items = items;
        this.address = address;
        this.comment = comment;
        this.status_id = status_id;
    }

    public CartDto(@NotNull String phone, @NotNull String address) {
        this.phone = phone;
        this.address = address;
    }

    public CartDto(@NotNull Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<ItemDto> getItems() {
        return items;
    }

    public void setItems(Collection<ItemDto> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }
}
