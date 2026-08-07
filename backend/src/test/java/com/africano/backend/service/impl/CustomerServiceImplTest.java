package com.africano.backend.service.impl;

import com.africano.backend.entity.Customer;
import com.africano.backend.entity.DocumentType;
import com.africano.backend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.africano.backend.exception.CustomerNotFoundException;


import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        UUID id = UUID.randomUUID();

        when(customerRepository.findById(id))
                .thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.findById(id)
        );

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    @Test
    void shouldCreateCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Jose");
        customer.setLastName("Africano");
        customer.setEmail("jose@example.com");

        when(customerRepository.existsByEmail(customer.getEmail()))
                .thenReturn(false);

        when(customerRepository.existsByDocumentTypeAndDocumentNumber(
                customer.getDocumentType(),
                customer.getDocumentNumber()))
                .thenReturn(false);

        when(customerRepository.save(customer))
                .thenReturn(customer);

        Customer result = customerService.create(customer);

        assertEquals("Jose", result.getFirstName());
        assertEquals("Africano", result.getLastName());
        assertEquals("jose@example.com", result.getEmail());
    }
    @Test
    void shouldRejectDuplicateEmail() {

        Customer customer = new Customer();
        customer.setFirstName("Pedro");
        customer.setLastName("Gomez");
        customer.setEmail("jose@example.com");

        when(customerRepository.existsByEmail(customer.getEmail()))
                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.create(customer)
        );

        assertEquals(
                "El email ya está registrado",
                exception.getMessage()
        );
    }
    @Test
    void shouldRejectDuplicateDocument() {

        Customer customer = new Customer();
        customer.setFirstName("Pedro");
        customer.setLastName("Gomez");
        customer.setDocumentType(DocumentType.CE);
        customer.setDocumentNumber("123456789");
        customer.setEmail("pedro@example.com");

        when(customerRepository.existsByEmail(customer.getEmail()))
                .thenReturn(false);

        when(customerRepository.existsByDocumentTypeAndDocumentNumber(
                customer.getDocumentType(),
                customer.getDocumentNumber()))
                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.create(customer)
        );

        assertEquals(
                "El documento ya está registrado",
                exception.getMessage()
        );
    }

    @Test
    void shouldUpdateCustomer() {

        UUID id = UUID.randomUUID();

        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("Jose");
        existingCustomer.setLastName("Africano");
        existingCustomer.setEmail("jose@example.com");

        Customer updatedData = new Customer();
        updatedData.setFirstName("Jose Luis");
        updatedData.setLastName("Africano Moya");
        updatedData.setEmail("joseluis@example.com");

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(existingCustomer));

        when(customerRepository.existsByEmailAndIdNot(
                updatedData.getEmail(), id))
                .thenReturn(false);

        when(customerRepository.existsByDocumentTypeAndDocumentNumberAndIdNot(
                updatedData.getDocumentType(),
                updatedData.getDocumentNumber(),
                id))
                .thenReturn(false);

        when(customerRepository.save(existingCustomer))
                .thenReturn(existingCustomer);

        Customer result = customerService.update(id, updatedData);

        assertEquals("Jose Luis", result.getFirstName());
        assertEquals("Africano Moya", result.getLastName());
        assertEquals("joseluis@example.com", result.getEmail());
    }
    @Test
    void shouldRejectUpdateWithDuplicateEmail() {

        UUID id = UUID.randomUUID();

        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("Jose");
        existingCustomer.setLastName("Africano");

        Customer updatedData = new Customer();
        updatedData.setFirstName("Jose Luis");
        updatedData.setLastName("Africano Moya");
        updatedData.setEmail("pedro@example.com");

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(existingCustomer));

        when(customerRepository.existsByEmailAndIdNot(
                updatedData.getEmail(), id))
                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.update(id, updatedData)
        );

        assertEquals(
                "El email ya está registrado",
                exception.getMessage()
        );
    }
    @Test
    void shouldRejectUpdateWithDuplicateDocument() {

        UUID id = UUID.randomUUID();

        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("Jose");
        existingCustomer.setLastName("Africano");

        Customer updatedData = new Customer();
        updatedData.setFirstName("Jose Luis");
        updatedData.setLastName("Africano Moya");
        updatedData.setDocumentType(DocumentType.CE);
        updatedData.setDocumentNumber("987654321");
        updatedData.setEmail("joseluis@example.com");

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(existingCustomer));

        when(customerRepository.existsByEmailAndIdNot(
                updatedData.getEmail(), id))
                .thenReturn(false);

        when(customerRepository.existsByDocumentTypeAndDocumentNumberAndIdNot(
                updatedData.getDocumentType(),
                updatedData.getDocumentNumber(),
                id))
                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.update(id, updatedData)
        );

        assertEquals(
                "El documento ya está registrado",
                exception.getMessage()
        );
    }
    @Test
    void shouldDeleteCustomer() {

        UUID id = UUID.randomUUID();

        when(customerRepository.existsById(id))
                .thenReturn(true);

        customerService.deleteById(id);

        verify(customerRepository).deleteById(id);
    }
    @Test
    void shouldRejectDeleteWhenCustomerDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(customerRepository.existsById(id))
                .thenReturn(false);

        CustomerNotFoundException exception = assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.deleteById(id)
        );

        assertEquals(
                "Cliente no encontrado",
                exception.getMessage()
        );

        verify(customerRepository, never()).deleteById(id);
    }
}