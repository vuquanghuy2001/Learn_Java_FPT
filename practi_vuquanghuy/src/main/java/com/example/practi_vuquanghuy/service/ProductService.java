package com.example.practi_vuquanghuy.service;

import com.example.practi_vuquanghuy.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public void addProduct(Product theProduct);
    public void buyProduct(Product theProduct);
}
