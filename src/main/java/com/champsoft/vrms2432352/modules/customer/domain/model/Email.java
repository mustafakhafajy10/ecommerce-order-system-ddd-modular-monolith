package com.champsoft.vrms2432352.modules.customer.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;

public record Email(String value) {
    public Email {
        if (value == null || value.isBlank() || !value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new BadRequestException("Customer email must be valid");
        }
    }
}
