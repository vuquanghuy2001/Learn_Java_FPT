package com.example.ss3.service;

import com.example.ss3.dto.RecipeDto;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.RecipeEntity;
import com.example.ss3.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepo recipeRepo;

    @Override
    public Page<RecipeEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.recipeRepo.findAll(pageable);
    }

    @Override
    public List<RecipeEntity> getTop3ByCategory(int id) {
        return recipeRepo.findTop3ByCategory_id(id);
    }

    @Override
    public List<RecipeEntity> getAll() {
        return recipeRepo.findAll();
    }

    @Override
    public Page<RecipeEntity> findByCategory(String category, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return recipeRepo.findAllByCategory_Name(category,pageable);
    }

    @Override
    public RecipeEntity findByID(Integer id) {
        return recipeRepo.findById(id).get();
    }

    @Override
    public void add(RecipeDto recipeDto) {
        RecipeEntity recipeEntity = new RecipeEntity(
                recipeDto.getName(),recipeDto.getDescription(),recipeDto.getCategory_id()
        );
        recipeRepo.save(recipeEntity);

    }


    @Override
    public RecipeEntity save(Integer id, RecipeDto recipeDto) {
        RecipeEntity recipeEntity = findByID(id);
        recipeEntity.setName(recipeDto.getName());
        recipeEntity.setDescription(recipeDto.getDescription());
        recipeEntity.setCategory_id(recipeDto.getCategory_id());
        return recipeRepo.save(recipeEntity);
    }
    @Override
    public void delete(Integer id) {
        RecipeEntity recipeEntity = findByID(id);
        if(recipeEntity!=null)
            recipeRepo.delete(recipeEntity);

    }

    @Override
    public long getTotal() {
       return recipeRepo.count();
    }
}
