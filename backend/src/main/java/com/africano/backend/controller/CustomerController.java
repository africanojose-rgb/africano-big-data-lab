package com.africano.backend.controller;

import com.africano.backend.dto.CustomerCreateRequest;
import com.africano.backend.dto.CustomerResponse;
import com.africano.backend.dto.CustomerUpdateRequest;
import com.africano.backend.entity.Customer;
import com.africano.backend.mapper.CustomerMapper;
import com.africano.backend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(
            CustomerService customerService,
            CustomerMapper customerMapper) {

        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(
            @Valid @RequestBody CustomerCreateRequest request) {

        Customer customer = customerMapper.toEntity(request);

        Customer savedCustomer = customerService.create(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {

        return customerService.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable UUID id) {

        Customer customer = customerService.findById(id);

        return customerMapper.toResponse(customer);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody CustomerUpdateRequest request) {

        Customer customer = customerMapper.toEntity(request);

        Customer updatedCustomer = customerService.update(id, customer);

        return customerMapper.toResponse(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        customerService.deleteById(id);
    }
}