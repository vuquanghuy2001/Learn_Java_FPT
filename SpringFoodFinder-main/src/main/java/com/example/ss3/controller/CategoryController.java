package com.example.ss3.controller;

import com.example.ss3.dto.CategoryDto;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.IngredientEntity;
import com.example.ss3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String index(Model model) {
        List<CategoryEntity> productList = categoryService.getAll();
        model.addAttribute("productList", productList);
        return findPaginated(1, model);
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<CategoryEntity> page = categoryService.findPaginated(pageNo, pageSize);
        List< CategoryEntity > listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "/category/list";
    }

    @GetMapping("/create")
    public String form(Model model) {
        return "category/form";
    }


    @PostMapping("/create")
    public String create(@RequestParam Map<String, String> params, Model model, @ModelAttribute("categoryDto")
            CategoryDto categoryDto) {
        String name = params.get("name");
        String image = params.get("image");
        CategoryDto dto = new CategoryDto(name,image);
        categoryService.add(dto);
        return "redirect:/admin/category";
    }

    @GetMapping("/update/{id}")
    public  String update(@PathVariable(value = "id") int id,Model model){
        CategoryEntity categoryEntity = categoryService.findByID(id);
        model.addAttribute("category",categoryEntity);
        return "category/update";
    }

    @PostMapping("/update/save")
    public String save(Model model,@RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("id"));
        String name = params.get("name");
        String image = params.get("image");
        categoryService.save(id,name,image);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id,Model model) {
        categoryService.delete(id);
        return  "redirect:/admin/category";
    }

}
