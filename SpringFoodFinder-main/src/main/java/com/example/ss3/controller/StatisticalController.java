package com.example.ss3.controller;

import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.RecipeEntity;
import com.example.ss3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/statistical")
public class StatisticalController {
    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DishService dishService;
    @Autowired
    CartService cartService;
    @Autowired
    UserCustomService userCustomService;
    @GetMapping
    public String index(Model model) {
        long totalDish = dishService.getTotal();
        long totalIngredient = ingredientService.pageCount();
        long totalCategory = categoryService.getTotal();
        long totalRecipe = recipeService.getTotal();
        long totalCustomer = userCustomService.getTotal();
        long totalCart = cartService.getTotal();
        model.addAttribute("totalDish", totalDish);
        model.addAttribute("totalIngredient", totalIngredient);
        model.addAttribute("totalCategory", totalCategory);
        model.addAttribute("totalRecipe", totalRecipe);
        model.addAttribute("totalCustomer", totalCustomer);
        model.addAttribute("totalCart", totalCart);
        return "statistical/index";
    }

    @GetMapping("/dish")
    public  String dish(Model model){
        List<CategoryEntity> listCategory = categoryService.getAll();
        Map<String,Long> dishCategory = new HashMap<String,Long>();
        for (CategoryEntity c: listCategory
             ) {
            long count = dishService.countByCategory(c);
            dishCategory.put(c.getName(),count);
        }
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("dishCategory", dishCategory);
        return "statistical/dish";
    }
}
