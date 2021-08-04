package com.example.customerapidemo.rest;


import com.example.customerapidemo.dao.CustomerRepository;
import com.example.customerapidemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {


    private CustomerRepository repository;
    //private EmployeeService repository;

    @Autowired
    public CustomerRestController(CustomerRepository theCustomerRepository) {
        repository = theCustomerRepository;
    }

    @GetMapping("/cus")
    List<Customer> all() {
        return repository.findAll();
    }

    @PostMapping("/cus")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    @GetMapping("/cus/{id}")
    Customer one(@PathVariable Integer id) {
        return repository.findById(id).
                orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @DeleteMapping("/cus/{id}")
    void deleteEmployee(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @PutMapping("/cus/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Integer id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newCustomer.getFirstName());
                    employee.setLastName(newCustomer.getLastName());
                    employee.setEmail(newCustomer.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }
}