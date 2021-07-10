package com.example.springsecurity.service;

import com.example.springsecurity.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int theId);

    public void save(Employee theEmployee);

    public void deletebyId(int theId);

    public List<Employee> searchBy(String theName);
}
