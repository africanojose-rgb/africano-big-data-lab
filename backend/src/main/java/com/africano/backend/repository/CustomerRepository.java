package com.africano.backend.repository;

import com.africano.backend.entity.Customer;
import com.africano.backend.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByEmail(String email);

    boolean existsByDocumentTypeAndDocumentNumber(
            DocumentType documentType,
            String documentNumber
    );

    boolean existsByEmailAndIdNot(
            String email,
            UUID id
    );

    boolean existsByDocumentTypeAndDocumentNumberAndIdNot(
            DocumentType documentType,
            String documentNumber,
            UUID id
    );
}