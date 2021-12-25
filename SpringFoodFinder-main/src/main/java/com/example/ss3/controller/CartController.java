package com.example.ss3.controller;

import com.example.ss3.entity.CartEntity;
import com.example.ss3.entity.CategoryEntity;
import com.example.ss3.entity.StatusEntity;
import com.example.ss3.service.CartService;
import com.example.ss3.service.StatusService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    StatusService statusService;
    @GetMapping
    public String index(Model model) {
        List<CartEntity> productList = cartService.getAll();
        model.addAttribute("productList", productList);
        return findPaginated(1, model);
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<CartEntity> page = cartService.findPaginated(pageNo, pageSize);
        List<CartEntity> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "/cart/list";
    }

    @GetMapping("/update/{id}")
    public  String update(@PathVariable(value = "id") int id,Model model){
        CartEntity cartEntity = cartService.findByID(id);
        List<StatusEntity> statusEntities = statusService.getAll();
        model.addAttribute("cart",cartEntity);
        model.addAttribute("status",statusEntities);
        return "cart/update";
    }

    @PostMapping("/update/save")
    public String save(Model model,@RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("id"));
        Integer status_id = Integer.valueOf(params.get("status_id"));
        String comment = params.get("comment");
        cartService.updateStatus(id,status_id,comment);
        return "redirect:/admin/cart";
    }

    @GetMapping("/status/{id}")
    public  String findByStatusDefault(@PathVariable(value = "id") int id,Model model){
        Map<String,String> map = new HashMap<String, String>();
        map.put("id", String.valueOf(id));
        map.put("pageNo", "1");
        return findByStatusPaginated(map,model);
    }

    @GetMapping("/status/{id}/{pageNo}")
    public String findByStatusPaginated(@PathVariable Map<String, String> pathVarsMap, Model model) {
        int pageSize = 5;
        Integer id = Integer.valueOf(pathVarsMap.get("id"));
        Integer pageNo = Integer.valueOf(pathVarsMap.get("pageNo"));

        Page<CartEntity> page = cartService.findByStatus(id,pageNo, pageSize);
        List<CartEntity> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "/cart/list";
    }

}
