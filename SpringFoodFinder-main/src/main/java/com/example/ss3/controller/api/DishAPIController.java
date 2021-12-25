package com.example.ss3.controller.api;

import com.example.ss3.entity.DishEntity;
import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
public class DishAPIController {
    @Autowired
    DishService dishService;
    @GetMapping()
    public ResponseEntity getDishPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit) {
        BaseResponse res = new BaseResponse();
        res.data = dishService.findPaginated(page,limit).getContent();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/category")
    public ResponseEntity getDishByCategoryPage(
            @RequestParam(value = "name", required = false, defaultValue = "Popular") String category,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit)
    {
        BaseResponse res = new BaseResponse();
        res.data = dishService.findByCategory(category,page,limit).getContent();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/ingredient")
    public ResponseEntity getDishByIngredientPage(
            @RequestParam(value = "name", required = false, defaultValue = "apple") String ingredient,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit)
    {
        BaseResponse res = new BaseResponse();
        res.data = dishService.findByIngredient(ingredient,page,limit).getContent();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/ingredients")
    public ResponseEntity getDishByIngredientsPage(
            @RequestParam(value = "name", required = false, defaultValue = "apple") List<String> ingredients,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit)
    {
        BaseResponse res = new BaseResponse();
        res.data = dishService.findByIngredients(ingredients,page,limit).getContent();
        return ResponseEntity.ok(res);
    }
}
