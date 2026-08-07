package com.africano.backend.mapper;

import com.africano.backend.dto.CustomerCreateRequest;
import com.africano.backend.dto.CustomerResponse;
import com.africano.backend.dto.CustomerUpdateRequest;
import com.africano.backend.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerCreateRequest request) {

        Customer customer = new Customer();

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setDocumentType(request.documentType());
        customer.setDocumentNumber(request.documentNumber());
        customer.setEmail(request.email());

        return customer;
    }

    public Customer toEntity(CustomerUpdateRequest request) {

        Customer customer = new Customer();

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setDocumentType(request.documentType());
        customer.setDocumentNumber(request.documentNumber());
        customer.setEmail(request.email());

        return customer;
    }

    public CustomerResponse toResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getDocumentType(),
                customer.getDocumentNumber(),
                customer.getEmail(),
                customer.getCreatedAt()
        );
    }
}