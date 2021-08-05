package com.example.crudrestapi.service;


import com.example.crudrestapi.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public Customer getCustomer(int theId);

    public void saveCustomer(Customer theEmployee);

    public void deleteCustomer(int theId);

}