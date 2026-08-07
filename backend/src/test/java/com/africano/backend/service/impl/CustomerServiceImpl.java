package com.africano.backend.service.impl;

import com.africano.backend.entity.Customer;
import com.africano.backend.exception.CustomerNotFoundException;
import com.africano.backend.repository.CustomerRepository;
import com.africano.backend.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        if (customerRepository.existsByDocumentTypeAndDocumentNumber(
                customer.getDocumentType(),
                customer.getDocumentNumber())) {

            throw new IllegalArgumentException("El documento ya está registrado");
        }

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Cliente no encontrado")
                );
    }

    @Override
    public Customer update(UUID id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Cliente no encontrado")
                );

        if (customerRepository.existsByEmailAndIdNot(
                customer.getEmail(), id)) {

            throw new IllegalArgumentException("El email ya está registrado");
        }

        if (customerRepository.existsByDocumentTypeAndDocumentNumberAndIdNot(
                customer.getDocumentType(),
                customer.getDocumentNumber(),
                id)) {

            throw new IllegalArgumentException("El documento ya está registrado");
        }

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setDocumentType(customer.getDocumentType());
        existingCustomer.setDocumentNumber(customer.getDocumentNumber());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }
    
    @Override
    public void deleteById(UUID id) {

        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Cliente no encontrado");
        }

        customerRepository.deleteById(id);
    }
}