package com.example.practi_vuquanghuy.dao;

import com.example.practi_vuquanghuy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
