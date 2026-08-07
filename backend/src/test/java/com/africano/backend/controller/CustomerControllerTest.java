package com.africano.backend.controller;

import com.africano.backend.dto.CustomerCreateRequest;
import com.africano.backend.dto.CustomerResponse;
import com.africano.backend.dto.CustomerUpdateRequest;
import com.africano.backend.entity.Customer;
import com.africano.backend.entity.DocumentType;
import com.africano.backend.mapper.CustomerMapper;
import com.africano.backend.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;


import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void shouldFindCustomerById() {

        UUID id = UUID.randomUUID();

        Customer customer = new Customer();
        customer.setFirstName("Jose");
        customer.setLastName("Africano");

        CustomerResponse response = new CustomerResponse(
                id,
                "Jose",
                "Africano",
                DocumentType.CE,
                "123456789",
                "jose@example.com",
                null
        );

        when(customerService.findById(id))
                .thenReturn(customer);

        when(customerMapper.toResponse(customer))
                .thenReturn(response);

        CustomerResponse result =
                customerController.findById(id);

        assertEquals("Jose", result.firstName());
        assertEquals("Africano", result.lastName());

        verify(customerService).findById(id);
        verify(customerMapper).toResponse(customer);
    }
    @Test
    void shouldCreateCustomer() {

        CustomerCreateRequest request = new CustomerCreateRequest(
                "Jose",
                "Africano",
                DocumentType.CE,
                "123456789",
                "jose@example.com"
        );

        Customer customer = new Customer();

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName("Jose");
        savedCustomer.setLastName("Africano");

        UUID id = UUID.randomUUID();

        CustomerResponse response = new CustomerResponse(
                id,
                "Jose",
                "Africano",
                DocumentType.CE,
                "123456789",
                "jose@example.com",
                null
        );

        when(customerMapper.toEntity(request))
                .thenReturn(customer);

        when(customerService.create(customer))
                .thenReturn(savedCustomer);

        when(customerMapper.toResponse(savedCustomer))
                .thenReturn(response);

        CustomerResponse result =
                customerController.create(request);

        assertEquals("Jose", result.firstName());
        assertEquals("Africano", result.lastName());

        verify(customerMapper).toEntity(request);
        verify(customerService).create(customer);
        verify(customerMapper).toResponse(savedCustomer);
    }
    @Test
    void shouldFindAllCustomers() {

        Customer customer1 = new Customer();
        customer1.setFirstName("Jose");
        customer1.setLastName("Africano");

        Customer customer2 = new Customer();
        customer2.setFirstName("Carlos");
        customer2.setLastName("Mendoza");

        CustomerResponse response1 = new CustomerResponse(
                UUID.randomUUID(),
                "Jose",
                "Africano",
                DocumentType.CE,
                "123456789",
                "jose@example.com",
                null
        );

        CustomerResponse response2 = new CustomerResponse(
                UUID.randomUUID(),
                "Carlos",
                "Mendoza",
                DocumentType.CC,
                "987654321",
                "carlos@example.com",
                null
        );

        when(customerService.findAll())
                .thenReturn(List.of(customer1, customer2));

        when(customerMapper.toResponse(customer1))
                .thenReturn(response1);

        when(customerMapper.toResponse(customer2))
                .thenReturn(response2);

        List<CustomerResponse> result =
                customerController.findAll();

        assertEquals(2, result.size());
        assertEquals("Jose", result.get(0).firstName());
        assertEquals("Carlos", result.get(1).firstName());

        verify(customerService).findAll();
        verify(customerMapper).toResponse(customer1);
        verify(customerMapper).toResponse(customer2);
    }

    @Test
    void shouldUpdateCustomer() {

        UUID id = UUID.randomUUID();

        CustomerUpdateRequest request = new CustomerUpdateRequest(
                "Jose Luis",
                "Africano",
                DocumentType.CE,
                "123456789",
                "jose@example.com"
        );

        Customer customer = new Customer();

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jose Luis");
        updatedCustomer.setLastName("Africano");

        CustomerResponse response = new CustomerResponse(
                id,
                "Jose Luis",
                "Africano",
                DocumentType.CE,
                "123456789",
                "jose@example.com",
                null
        );

        when(customerMapper.toEntity(request))
                .thenReturn(customer);

        when(customerService.update(id, customer))
                .thenReturn(updatedCustomer);

        when(customerMapper.toResponse(updatedCustomer))
                .thenReturn(response);

        CustomerResponse result =
                customerController.update(id, request);

        assertEquals("Jose Luis", result.firstName());
        assertEquals("Africano", result.lastName());

        verify(customerMapper).toEntity(request);
        verify(customerService).update(id, customer);
        verify(customerMapper).toResponse(updatedCustomer);
    }

    @Test
    void shouldDeleteCustomer() {

        UUID id = UUID.randomUUID();

        customerController.delete(id);

        verify(customerService).deleteById(id);
    }
}