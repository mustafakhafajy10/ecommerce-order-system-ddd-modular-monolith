package com.champsoft.vrms2432352.modules.product.application.service;

import com.champsoft.vrms2432352.modules.product.application.exception.DuplicateProductException;
import com.champsoft.vrms2432352.modules.product.application.exception.ProductNotFoundException;
import com.champsoft.vrms2432352.modules.product.application.port.out.ProductRepositoryPort;
import com.champsoft.vrms2432352.modules.product.domain.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCrudService {
    private final ProductRepositoryPort repository;

    public ProductCrudService(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    public Product createProduct(String name, BigDecimal price) {
        repository.findByNameIgnoreCase(name).ifPresent(existing -> {
            throw new DuplicateProductException(name);
        });
        return repository.save(Product.create(name, price));
    }

    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProduct(String id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public Product updateProduct(String id, String name, BigDecimal price) {
        var product = getProduct(id);
        repository.findByNameIgnoreCase(name)
            .filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new DuplicateProductException(name);
            });
        product.update(name, price);
        return repository.save(product);
    }

    @Transactional
    public void deleteProduct(String id) {
        getProduct(id);
        repository.deleteById(id);
    }
}
