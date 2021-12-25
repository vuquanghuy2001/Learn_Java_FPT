package com.example.ss3.service;

import com.example.ss3.dto.RecipeDto;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.IngredientEntity;
import com.example.ss3.entity.RecipeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipeService {
    Page<RecipeEntity> findPaginated(int pageNo, int pageSize);
    List<RecipeEntity> getTop3ByCategory(int id);
    List<RecipeEntity> getAll();
    Page<RecipeEntity> findByCategory(String category, int pageNo, int pageSize);
    RecipeEntity findByID(Integer id);
    void add(RecipeDto recipeDto);

    RecipeEntity save(Integer id,RecipeDto recipeDto);

    void delete(Integer id);
    long getTotal();
}
