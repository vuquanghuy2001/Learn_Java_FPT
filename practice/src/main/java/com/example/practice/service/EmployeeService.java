package com.example.practice.service;

import com.example.practice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAllEmployees();

    public void createEmployee(Employee theEmployee);
}
