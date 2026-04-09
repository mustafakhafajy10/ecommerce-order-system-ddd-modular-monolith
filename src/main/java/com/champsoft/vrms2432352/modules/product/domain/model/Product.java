package com.champsoft.vrms2432352.modules.product.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;
import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String id;
    private String name;
    private Money price;

    private Product(String id, String name, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Product name cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.price = new Money(price);
    }

    public static Product create(String name, BigDecimal price) {
        return new Product(UUID.randomUUID().toString(), name, price);
    }

    public static Product rehydrate(String id, String name, BigDecimal price) {
        return new Product(id, name, price);
    }

    public void update(String name, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Product name cannot be empty");
        }
        this.name = name;
        this.price = new Money(price);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.value();
    }
}
