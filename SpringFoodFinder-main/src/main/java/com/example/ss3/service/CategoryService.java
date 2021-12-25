package com.example.ss3.service;

import com.example.ss3.dto.CategoryDto;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAll();
    Page<CategoryEntity> findPaginated(int pageNo, int pageSize);
    CategoryEntity findByID(Integer id);
    void  add(CategoryDto categoryDto);
    CategoryEntity save(Integer id,String name, String image);
    void delete(Integer id);
    long getTotal();
}
