package com.example.customerapidemo.service;

import com.example.customerapidemo.dao.CustomerRepository;
import com.example.customerapidemo.entity.Customer;
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
    public List<Customer> findAll() {
        List<Customer> employees = repository.findAll();
        return employees;
    }

    @Override
    public Customer findById(int theId) {
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
    public void save(Customer theEmployee) {
        repository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        repository.deleteById(theId);
    }
}
