package com.example.ss3.controller.api;

import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
public class RecipeAPIController {
    @Autowired
    RecipeService recipeService;
    @GetMapping()
    public ResponseEntity getRecipePage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit) {
        BaseResponse res = new BaseResponse();
        res.data = recipeService.findPaginated(page,limit).getContent();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/category")
    public ResponseEntity getRecipeByCategoryPage(
            @RequestParam(value = "name", required = false, defaultValue = "Popular") String name,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit) {
        BaseResponse res = new BaseResponse();
        res.data = recipeService.findByCategory(name,page,limit).getContent();
        return ResponseEntity.ok(res);
    }
}
