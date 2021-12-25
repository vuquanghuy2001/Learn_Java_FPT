package com.example.demo.controller;

import com.example.demo.model.Sale;
import com.example.demo.response.SaleReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@SpringBootApplication
public class SaleController {

    @Autowired
    private SaleReponsitory saleReponsitory;

    @GetMapping("/sale")
    public String getAll(Model model) {
        List<Sale> sales = saleReponsitory.findAll();
        model.addAttribute("sales", sales);
        return "list_sales";
    }
}
