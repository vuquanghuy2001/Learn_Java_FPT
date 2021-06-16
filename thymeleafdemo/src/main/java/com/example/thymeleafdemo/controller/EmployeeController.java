package com.example.thymeleafdemo.controller;


import com.example.thymeleafdemo.model.Employee;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> theEmployees;

    @PostConstruct
    private void loadData() {
        Employee emp1 = new Employee(1,"Vu","Huy","vuquanghuyxl1234@gmail.com");
        Employee emp2 = new Employee(2,"Truong","Trinh","trinh55200@gmail.com");

        theEmployees = new ArrayList<>();

        theEmployees.add(emp1);
        theEmployees.add(emp2);
    }

    @GetMapping("/list")
    public String listEmplyees(Model theModel){
        theModel.addAttribute("employees", theEmployees);
        return "list_employees";
    }
}
