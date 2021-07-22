package com.example.practice.controller;

import com.example.practice.entity.Employee;
import com.example.practice.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String findAllEmployees(Model theModel) {

        // get employees from db
        List<Employee> theEmployees = employeeService.findAllEmployees();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "/employees/list-employees";
    }

    @PostMapping("/save")
    public String createEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.createEmployee(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

}
