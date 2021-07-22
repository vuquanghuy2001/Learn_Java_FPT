package com.example.practice.service;

import com.example.practice.dao.EmployeeRepository;
import com.example.practice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAllByOrderByNameAsc();
	}

	@Override
	public void createEmployee(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

}






