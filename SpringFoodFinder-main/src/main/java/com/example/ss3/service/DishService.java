package com.example.ss3.service;

import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DishService {
    List<DishEntity> getAllDish();
    DishEntity findByID(Integer id);
    Page<DishEntity> findByCategory(String category,int pageNo, int pageSize);
    Page<DishEntity> findByIngredient(String ingredient,int pageNo, int pageSize);
    Page<DishEntity> findByIngredients(List<String> ingredients,int pageNo, int pageSize);
    Page<DishEntity> findByRecipe(int pageNo, int pageSize);
    void addDish(DishEntity dishEntity);
    void saveDish(Integer id,String name, Integer category_id, Integer recipe_id, String image_url,String method, String ingredient_des);
    void delete(Integer id);
    Page<DishEntity> findPaginated(int pageNo, int pageSize);
    long getTotal();
    long countByCategory(CategoryEntity categoryEntity);

}
