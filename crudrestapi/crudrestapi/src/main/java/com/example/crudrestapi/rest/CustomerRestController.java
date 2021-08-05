package com.example.crudrestapi.rest;


import com.example.crudrestapi.entity.Customer;
import com.example.crudrestapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/cus")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/cus")
    public Customer newCustomer(@RequestBody Customer newCustomer) {
        newCustomer.setId(0);
        customerService.saveCustomer(newCustomer);
        return newCustomer;
    }

    @GetMapping("/cus/{id}")
    public Customer getCustomer(@PathVariable int id) {
        Customer theCustomer = customerService.getCustomer(id);
        if(theCustomer == null){
            throw new CustomerNotFoundException("Customer id not found - " + id);
        }
        return theCustomer;
    }

    @DeleteMapping("/cus/{id}")
    public String deleteEmployee(@PathVariable int id){
        Customer tempCustomer = customerService.getCustomer(id);

        if(tempCustomer == null){
            throw new CustomerNotFoundException("Customer id not found - " + id);
        }

        customerService.deleteCustomer(id);
        return "Deleted customer id - " + id;
    }

    @PutMapping("/cus/{id}")
    public Customer replaceCustomer(@RequestBody Customer newCustomer) {
        customerService.saveCustomer(newCustomer);
        return newCustomer;
    }
}