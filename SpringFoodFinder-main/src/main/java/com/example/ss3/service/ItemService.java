package com.example.ss3.service;

import com.example.ss3.dto.ItemDto;
import com.example.ss3.entity.ItemEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    List<ItemEntity> getAll();
    Page<ItemEntity> findPaginated(int pageNo, int pageSize);
    ItemEntity findByID(Integer id);
    void  add(ItemDto itemDto);
    ItemEntity save(ItemDto itemDto);
    void delete(Integer id);
    String removeFromCart(Integer id);
    ItemEntity addToCart(ItemDto itemDto);
    ItemEntity updateItemFromCart(ItemDto itemDto);
}
