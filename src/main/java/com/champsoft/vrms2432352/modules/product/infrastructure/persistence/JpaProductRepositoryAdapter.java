package com.champsoft.vrms2432352.modules.product.infrastructure.persistence;

import com.champsoft.vrms2432352.modules.product.application.port.out.ProductRepositoryPort;
import com.champsoft.vrms2432352.modules.product.domain.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JpaProductRepositoryAdapter implements ProductRepositoryPort {
    private final SpringDataProductRepository repository;

    public JpaProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        var entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        var saved = repository.save(entity);
        return Product.rehydrate(saved.getId(), saved.getName(), saved.getPrice());
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
            .map(entity -> Product.rehydrate(entity.getId(), entity.getName(), entity.getPrice()))
            .toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return repository.findById(id)
            .map(entity -> Product.rehydrate(entity.getId(), entity.getName(), entity.getPrice()));
    }

    @Override
    public Optional<Product> findByNameIgnoreCase(String name) {
        return repository.findByNameIgnoreCase(name)
            .map(entity -> Product.rehydrate(entity.getId(), entity.getName(), entity.getPrice()));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
