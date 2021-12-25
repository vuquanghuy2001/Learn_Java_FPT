package com.example.ss3.controller.api;

import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.CategoryService;
import com.example.ss3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryAPIController {
    @Autowired
    CategoryService categoryService;
    @GetMapping()
    public ResponseEntity getDishPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {
        BaseResponse res = new BaseResponse();
        res.data = categoryService.findPaginated(page,limit).getContent();
        return ResponseEntity.ok(res);
    }
}
