package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.response.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@SpringBootApplication
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String getAll (Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "list_products";
    }
}
