package com.example.ss3.service;

import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepo dishRepo;
    @Override
    public List<DishEntity> getAllDish() {
        return dishRepo.findAll();
    }

    @Override
    public DishEntity findByID(Integer id) {
        return dishRepo.findById(id).get();
    }

    @Override
    public Page<DishEntity> findByCategory(String category,int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.dishRepo.findDishByCategory(category,pageable);
    }

    @Override
    public Page<DishEntity> findByIngredient(String ingredient, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.dishRepo.findDishByIngredient(ingredient,pageable);
    }

    @Override
    public Page<DishEntity> findByIngredients(List<String> ingredients, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.dishRepo.findDishByIngredients(ingredients,pageable);
    }

    @Override
    public Page<DishEntity> findByRecipe(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void addDish(DishEntity dishEntity) {
        dishRepo.save(dishEntity);
    }

    @Override
    public void saveDish(Integer id,String name, Integer category_id, Integer recipe_id, String image_url, String method, String ingredient_des) {
        DishEntity dish = findByID(id);
        dish.setName(name);
        dish.setCategory_id(category_id);
        dish.setRecipe_id(recipe_id);
        dish.setImage_url(image_url);
        dish.setMethod(method);
        dish.setIngredient_des(ingredient_des);
        dishRepo.save(dish);
    }

    @Override
    public void delete(Integer id) {
        DishEntity dish = findByID(id);
        if(dish!=null)
        dishRepo.delete(dish);
    }

    @Override
    public Page<DishEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.dishRepo.findAll(pageable);
    }

    @Override
    public long getTotal() {
        return dishRepo.count();
    }

    @Override
    public long countByCategory(CategoryEntity categoryEntity) {
        return dishRepo.countByCategory(categoryEntity);
    }
}
