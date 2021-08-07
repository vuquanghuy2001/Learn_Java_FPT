package com.example.practi_vuquanghuy.service;

import com.example.practi_vuquanghuy.dao.ProductRepository;
import com.example.practi_vuquanghuy.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        return products;
    }

    @Override
    public void addProduct(Product theProduct) {
        repository.save(theProduct);
    }

    @Override
    public void buyProduct(Product theProduct) {

    }
}
