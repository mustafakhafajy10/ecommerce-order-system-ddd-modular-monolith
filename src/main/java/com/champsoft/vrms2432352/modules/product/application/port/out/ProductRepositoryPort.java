package com.champsoft.vrms2432352.modules.product.application.port.out;

import com.champsoft.vrms2432352.modules.product.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(String id);
    Optional<Product> findByNameIgnoreCase(String name);
    void deleteById(String id);
}
