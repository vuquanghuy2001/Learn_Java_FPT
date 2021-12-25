package com.example.ss3.service;

import com.example.ss3.dto.CartDto;
import com.example.ss3.dto.CategoryDto;
import com.example.ss3.entity.CartEntity;
import com.example.ss3.entity.CategoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {
    List<CartEntity> getAll();
    Page<CartEntity> findPaginated(int pageNo, int pageSize);
    CartEntity findByID(Integer id);
    void updateStatus(int cart_id,int status_id,String comment);
    String  add(CartDto cartDto);
    List<CartEntity> findByUser(int pageNo, int pageSize);
    List<CartEntity> findAllByUser();
    Page<CartEntity> findByStatus(Integer id, int pageNo, int pageSize);
    long getTotal();
    Integer getCartId();
    Integer getCartIdByUserId(Integer userId);
    CartEntity getTempCart();
    CartEntity addCartUser(CartDto cartDto);
    Integer checkTempCartByDishId(Integer id);

}
