package com.example.empsecurity.rest;

import com.example.empsecurity.dao.EmployeeRepository;
import com.example.empsecurity.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeRestController(EmployeeRepository theEmployeeRepository) {
        repository = theEmployeeRepository;
    }

    @GetMapping("/emp")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/emp")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/emp/{id}")
    Employee one(@PathVariable Integer id) {
        return repository.findById(id).
                orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/emp/{id}")
    void deleteEmployee(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @PutMapping("/emp/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setEmail(newEmployee.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }
}
