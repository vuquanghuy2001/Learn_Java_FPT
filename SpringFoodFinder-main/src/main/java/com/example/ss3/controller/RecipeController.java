package com.example.ss3.controller;

import com.example.ss3.dto.RecipeDto;
import com.example.ss3.dto.UserDto;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.RecipeEntity;
import com.example.ss3.service.CategoryService;
import com.example.ss3.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String index(Model model) {
        List<RecipeEntity> productList = recipeService.getAll();
        model.addAttribute("productList", productList);
        return findPaginated(1, model);
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<RecipeEntity> page = recipeService.findPaginated(pageNo, pageSize);
        List< RecipeEntity > listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "/recipe/list";
    }
    public void getInformation(Model model){
        List<CategoryEntity> categoryList = categoryService.getAll();
        model.addAttribute("category",categoryList);
    }

    @GetMapping("/create")
    public String form(Model model) {
        getInformation(model);
        return "recipe/form";
    }

    @ModelAttribute("recipeDto")
    RecipeDto recipeDto(){
        return new RecipeDto();
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam Map<String, String> params, Model model,
                         @Valid @ModelAttribute("recipeDto") RecipeDto recipeDto,
                         BindingResult bindingResult) {
//        String name = params.get("name");
//        Integer categoryId = Integer.valueOf(params.get("category_id"));
//        String description = params.get("description");
//        RecipeDto dto = new RecipeDto(name,description,categoryId);
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/recipe/create";
        }
        recipeService.add(recipeDto);
        return "redirect:/admin/recipe";
    }

    @GetMapping("/update/{id}")
    public  String update(@PathVariable(value = "id") int id,Model model){

        getInformation(model);
        RecipeEntity recipeEntity = recipeService.findByID(id);
        model.addAttribute("recipe",recipeEntity);
        return "recipe/update";
    }

    @PostMapping("/update/save")
    public String save(Model model,@RequestParam Map<String, String> params) throws IOException {
        Integer id = Integer.valueOf(params.get("id"));
        String name = params.get("name");
        Integer categoryId = Integer.valueOf(params.get("category_id"));
        String description = params.get("description");
        RecipeDto dto = new RecipeDto(name,description,categoryId);
        recipeService.save(id,dto);
        return "redirect:/admin/recipe";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id,Model model) {
        recipeService.delete(id);
        return  "redirect:/admin/recipe";
    }
}
