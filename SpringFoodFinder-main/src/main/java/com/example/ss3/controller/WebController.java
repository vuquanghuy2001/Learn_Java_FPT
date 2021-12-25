package com.example.ss3.controller;

import com.example.ss3.dto.UserDto;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.IngredientEntity;
import com.example.ss3.entity.ProductEntity;
import com.example.ss3.entity.RecipeEntity;
import com.example.ss3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;
import sun.reflect.generics.scope.Scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {
    @Autowired
    ProductService productService;
    @Autowired
    DishService dishService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    RecipeService recipeService;
    @Autowired
    UserCustomService userCustomService;

    @ModelAttribute("UserEntity")
    UserDto userDto(){
        return new UserDto();
    }

    //security
    @GetMapping("/login")
    public String login() {
        return "security/login";
    }
    @GetMapping(value = "/login",params = "error")
    public String error404(){
        return "security/404";
    }
    @GetMapping("/register")
    public String register() { return "security/register"; }
    @GetMapping("/error")
    public String error() {
        return "security/404";
    }
    @PostMapping("/register/save")
    public  String save(@ModelAttribute("UserEntity") UserDto userDto){
        userCustomService.save(userDto);
        return "redirect:/login?success";
    }


//    @GetMapping("/")
//    public String index(Model model) {
//        List<ProductEntity> productList = productService.getAllProduct();
//        model.addAttribute("productList", productList);
//
//
//        return findPaginated(1, model);
//    }

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }


        @GetMapping("/")
    public String index(Model model) {
//        Collection<IngredientEntity> ingredients = ingredientService.getIngredients();
//        model.addAttribute("ingredients", ingredients);
//            Collection<DishEntity> dishes = ingredientService.getDishes();
//            model.addAttribute("dishes", dishes);
            //rv_all_dish
            Page< DishEntity > page = dishService.findPaginated(1, 4);
            List < DishEntity > rv_all_dishes = page.getContent();
            model.addAttribute("dish1", rv_all_dishes.get(0));
            model.addAttribute("dish2", rv_all_dishes.get(1));
            model.addAttribute("dish3", rv_all_dishes.get(2));
            model.addAttribute("dish4", rv_all_dishes.get(3));
            //rv_popular_recipe
            List<RecipeEntity> rv_recipe = recipeService.getTop3ByCategory(1);
            List<Integer> rv_recipe_id = new ArrayList<>();
            for (RecipeEntity p: rv_recipe)
            {
                rv_recipe_id.add(p.getId());
            }

            model.addAttribute("rv_recipe", rv_recipe);

        return "/user_theme/index";
    }


    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page< ProductEntity > page = productService.findPaginated(pageNo, pageSize);
        List < ProductEntity > listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "index";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam Map<String, String> params){
        String name = params.get("name");
        Integer price = Integer.valueOf(params.get("price"));
        Integer quantity = Integer.valueOf(params.get("quantity"));
        Integer category = Integer.valueOf(params.get("category"));
        ProductEntity p = new ProductEntity(category,name,price,quantity);
        productService.addProduct(p);
        String result = "successfully added!";
        model.addAttribute("result",result);
        return "result";
    }

    @GetMapping("/delete")
    public String delete(Model model,  @RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("deleteid"));
        productService.deleteProduct(id);
        String result = "successfully deleted!";
        model.addAttribute("result",result);
        return "result";
    }

    @GetMapping("/update")
    public String update(Model model,  @RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("updateid"));
        String name = params.get("name");
        Integer price = checknull("price",params);
        Integer quantity = checknull("quantity",params);
        Integer category = checknull("category",params);

//        Integer quantity = Integer.valueOf(params.get("quantity"));
//        Integer category = Integer.valueOf(params.get("category"));

        productService.updateProduct(id,name,price,quantity,category);
        String result = "successfully updated!";
        model.addAttribute("result",result);
        return "result";

    }

    Integer checknull(String paramname, Map<String, String> params ){
        String value = params.get(paramname);
        if(!value.isEmpty()){
            return  Integer.valueOf(value);
        }
        return null;
    }

}
