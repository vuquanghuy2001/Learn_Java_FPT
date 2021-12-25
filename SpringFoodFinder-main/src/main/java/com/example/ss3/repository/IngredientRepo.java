package com.example.ss3.repository;

import com.example.ss3.entity.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo  extends JpaRepository<IngredientEntity, Integer> {
    public Page<IngredientEntity> findAllByNameContaining(String ingredient, Pageable pageable);
}
