package com.example.ss3.controller;

import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.IngredientEntity;
import com.example.ss3.entity.RecipeEntity;
import com.example.ss3.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;
    @GetMapping()
    public String index(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<IngredientEntity> page = ingredientService.findPaginated(pageNo, pageSize);
        List< IngredientEntity > listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "ingredient/list";
    }

    @GetMapping("/create")
    public String form(Model model) {
        return "ingredient/form";
    }


    @PostMapping("/create")
    public String create(@RequestParam Map<String, String> params, Model model, @ModelAttribute("ingredientEntity")
            IngredientEntity ingredientEntity) {
        String name = params.get("name");
        String image = params.get("image");
        IngredientEntity entity = new IngredientEntity(name,image);
        ingredientService.add(entity);
        return "redirect:/admin/ingredient";
    }

    @GetMapping("/update/{id}")
    public  String update(@PathVariable(value = "id") int id,Model model){
        IngredientEntity ingredientEntity = ingredientService.findByID(id);
        model.addAttribute("ingredient",ingredientEntity);
        return "ingredient/update";
    }

    @PostMapping("/update/save")
    public String save(Model model,@RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("id"));
        String name = params.get("name");
        String image = params.get("image");
        ingredientService.save(id,name,image);
        return "redirect:/admin/ingredient";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id,Model model) {
        ingredientService.delete(id);
        return  "redirect:/admin/ingredient";
    }

}
