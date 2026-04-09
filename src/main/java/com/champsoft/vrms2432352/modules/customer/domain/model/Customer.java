package com.champsoft.vrms2432352.modules.customer.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;
import java.util.UUID;

public class Customer {
    private final String id;
    private String name;
    private Email email;

    private Customer(String id, String name, Email email) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Customer name cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Customer create(String name, String email) {
        return new Customer(UUID.randomUUID().toString(), name, new Email(email));
    }

    public static Customer rehydrate(String id, String name, String email) {
        return new Customer(id, name, new Email(email));
    }

    public void update(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Customer name cannot be empty");
        }
        this.name = name;
        this.email = new Email(email);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email.value();
    }
}
