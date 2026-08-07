package com.africano.backend.service;

import com.africano.backend.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer create(Customer customer);

    List<Customer> findAll();

    Customer findById(UUID id);

    Customer update(UUID id, Customer customer);
    
    void deleteById(UUID id);

    
}
