package com.example.ss3.service;

import com.example.ss3.dto.CategoryDto;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.IngredientEntity;
import com.example.ss3.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Page<CategoryEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public CategoryEntity findByID(Integer id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public void add(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity(
                categoryDto.getName(), categoryDto.getImage()
        );
        categoryRepo.save(categoryEntity);
    }

    @Override
    public CategoryEntity save(Integer id, String name, String image) {
        CategoryEntity categoryEntity = findByID(id);
        categoryEntity.setName(name);
        categoryEntity.setImage(image);
        return categoryRepo.save(categoryEntity);
    }
    @Override
    public void delete(Integer id) {
        CategoryEntity cate = findByID(id);
        if(cate!=null)
            categoryRepo.delete(cate);

    }

    @Override
    public long getTotal() {
        return categoryRepo.count();
    }
}
