package com.example.ss3.controller;

import com.example.ss3.dto.ProductDto;
import com.example.ss3.dto.UserDto;
import com.example.ss3.entity.*;
import com.example.ss3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserCustomService userCustomService;
    @Autowired
    DishService dishService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    RecipeService recipeService;
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("UserEntity")
    UserDto userDto(){
        return new UserDto();
    }

    //security
    @GetMapping("/login")
    public String login() {
        return "security/login";
    }
    @GetMapping("/register")
    public String register() { return "security/register"; }
    @GetMapping(value = "/login",params = "error")
    public String error404(){
        return "security/404";
    }
    @GetMapping("/error")
    public String error() {
        return "security/404";
    }
    @PostMapping("/register/save")
    public  String save(@ModelAttribute("UserEntity")UserDto userDto){
        userCustomService.save(userDto);
        return "redirect:/admin/login?success";
    }



    @GetMapping()
    public String index(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<DishEntity> page = dishService.findPaginated(pageNo, pageSize);
        List< DishEntity > listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "index";
    }

    @GetMapping("/create")
    public String form(Model model) {
        getInformation(model);
        return "form";
    }

    public void getInformation(Model model){
        List<RecipeEntity> recipeList = recipeService.getAll();
        List<CategoryEntity> categoryList = categoryService.getAll();
        model.addAttribute("recipe",recipeList);
        model.addAttribute("category",categoryList);
    }

    @PostMapping("/create")
    public String create(@RequestParam Map<String, String> params, Model model,@ModelAttribute("dishEntity")
                          DishEntity dishEntity) {
        String name = params.get("name");
        Integer category = Integer.valueOf(params.get("category_id"));
        Integer recipe = Integer.valueOf(params.get("recipe_id"));
        String image = params.get("image");
        String method = params.get("method");
        String ingredient_des = params.get("ingredient_des");
        DishEntity entity = new DishEntity(name,category,recipe,image,method,ingredient_des);
        dishService.addDish(entity);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public  String update(@PathVariable(value = "id") int id,Model model){

        getInformation(model);
        DishEntity dishEntity = dishService.findByID(id);
        model.addAttribute("dish",dishEntity);
        return "update";
    }

    @PostMapping("/update/save")
    public String save(Model model,@RequestParam Map<String, String> params) throws IOException {
        Integer id = Integer.valueOf(params.get("id"));
        String name = params.get("name");
        Integer category = Integer.valueOf(params.get("category_id"));
        Integer recipe = Integer.valueOf(params.get("recipe_id"));
        String image = params.get("image");
        String method = params.get("method");
        String ingredient_des = params.get("ingredient_des");
        dishService.saveDish(id,name,category,recipe,image,method,ingredient_des);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id,Model model) {
        dishService.delete(id);
        return  "redirect:/admin";
    }

}
