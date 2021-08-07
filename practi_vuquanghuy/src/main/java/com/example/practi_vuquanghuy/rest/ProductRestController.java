package com.example.practi_vuquanghuy.rest;

import com.example.practi_vuquanghuy.entity.Product;
import com.example.practi_vuquanghuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/pro")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/pro")
    public Product addProduct(@RequestBody Product newProduct) {
        newProduct.setId(0);
        productService.addProduct(newProduct);
        return newProduct;
    }
}
