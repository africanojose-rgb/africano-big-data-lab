package com.africano.backend.service.impl;

import com.africano.backend.entity.Customer;
import com.africano.backend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void shouldFindCustomerById() {

        UUID id = UUID.randomUUID();

        Customer customer = new Customer();
        customer.setFirstName("Jose");
        customer.setLastName("Africano");

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(customer));

        Customer result = customerService.findById(id);

        assertEquals("Jose", result.getFirstName());
        assertEquals("Africano", result.getLastName());
    }
}