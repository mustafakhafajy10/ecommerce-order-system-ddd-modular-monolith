package com.champsoft.vrms2432352.modules.inventory.infrastructure.persistence;

import com.champsoft.vrms2432352.modules.inventory.application.port.out.InventoryRepositoryPort;
import com.champsoft.vrms2432352.modules.inventory.domain.model.InventoryItem;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JpaInventoryRepositoryAdapter implements InventoryRepositoryPort {
    private final SpringDataInventoryRepository repository;

    public JpaInventoryRepositoryAdapter(SpringDataInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryItem save(InventoryItem inventoryItem) {
        var entity = new InventoryItemJpaEntity();
        entity.setId(inventoryItem.getId());
        entity.setProductId(inventoryItem.getProductId());
        entity.setQuantity(inventoryItem.getQuantity());
        var saved = repository.save(entity);
        return InventoryItem.rehydrate(saved.getId(), saved.getProductId(), saved.getQuantity());
    }

    @Override
    public List<InventoryItem> findAll() {
        return repository.findAll().stream()
            .map(entity -> InventoryItem.rehydrate(entity.getId(), entity.getProductId(), entity.getQuantity()))
            .toList();
    }

    @Override
    public Optional<InventoryItem> findById(String id) {
        return repository.findById(id)
            .map(entity -> InventoryItem.rehydrate(entity.getId(), entity.getProductId(), entity.getQuantity()));
    }

    @Override
    public Optional<InventoryItem> findByProductId(String productId) {
        return repository.findByProductId(productId)
            .map(entity -> InventoryItem.rehydrate(entity.getId(), entity.getProductId(), entity.getQuantity()));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
