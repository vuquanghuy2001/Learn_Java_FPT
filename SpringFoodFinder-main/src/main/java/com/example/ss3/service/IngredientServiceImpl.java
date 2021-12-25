package com.example.ss3.service;

import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.IngredientEntity;
import com.example.ss3.repository.DishRepo;
import com.example.ss3.repository.IngredientRepo;
import com.example.ss3.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientRepo ingredientRepo;
    @Autowired
    DishRepo dishRepo;
    @Override
    public List<IngredientEntity> getAllIngredient() {
        return ingredientRepo.findAll();
    }

    @Override
    public DishEntity getDish() {
        DishEntity queryResult = dishRepo.findById(1).get();
        //System.out.println(queryResult.getCategory());
        return  queryResult;
    }

    @Override
    public Collection<IngredientEntity> getIngredients() {
        DishEntity queryResult = dishRepo.findById(249).get();
        return queryResult.getIngredients();
    }

    @Override
    public Collection<DishEntity> getDishes() {
        IngredientEntity queryResult = ingredientRepo.findById(1).get();
        return queryResult.getDishes();
    }

    @Override
    public IngredientEntity findByID(Integer id) {
        return ingredientRepo.findById(id).get();
    }

    @Override
    public void add(IngredientEntity ingredientEntity) {
         ingredientRepo.save(ingredientEntity);
    }

    @Override
    public void save(Integer id, String name, String image) {
        IngredientEntity ingredientEntity = findByID(id);
        ingredientEntity.setName(name);
        ingredientEntity.setImage(image);
        ingredientRepo.save(ingredientEntity);
    }

    @Override
    public void delete(Integer id) {
        IngredientEntity ingredientEntity = findByID(id);
        if(ingredientEntity!=null)
        ingredientRepo.delete(ingredientEntity);
    }

    @Override
    public long pageCount() {
        return ingredientRepo.count();
    }

    @Override
    public Page<IngredientEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.ingredientRepo.findAll(pageable);
    }

    @Override
    public Page<IngredientEntity> findByName(String ingredient, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.ingredientRepo.findAllByNameContaining(ingredient,pageable);
    }

}
