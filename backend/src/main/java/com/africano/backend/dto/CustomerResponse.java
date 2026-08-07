package com.africano.backend.dto;

import com.africano.backend.entity.DocumentType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(

        UUID id,
        String firstName,
        String lastName,
        DocumentType documentType,
        String documentNumber,
        String email,
        LocalDateTime createdAt

) {
}
