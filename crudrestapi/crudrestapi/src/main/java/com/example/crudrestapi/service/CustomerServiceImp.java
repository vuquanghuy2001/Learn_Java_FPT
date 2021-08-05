package com.example.crudrestapi.service;

import com.example.crudrestapi.dao.CustomerRepository;
import com.example.crudrestapi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService{


    private CustomerRepository repository;

    @Autowired
    public CustomerServiceImp(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> employees = repository.findAll();
        return employees;
    }

    @Override
    public Customer getCustomer(int theId) {
        Optional<Customer> result =repository.findById(theId);

        Customer theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void saveCustomer(Customer theEmployee) {
        repository.save(theEmployee);
    }

    @Override
    public void deleteCustomer(int theId) {
        repository.deleteById(theId);
    }
}
