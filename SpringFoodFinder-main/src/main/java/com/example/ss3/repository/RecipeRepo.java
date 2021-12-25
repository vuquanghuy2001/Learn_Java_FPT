package com.example.ss3.repository;

import com.example.ss3.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepo extends JpaRepository<RecipeEntity, Integer> {
    List<RecipeEntity> findTop3ByCategory_id(int id);
    Page<RecipeEntity> findAllByCategory_Name(String category, Pageable pageable);
}
