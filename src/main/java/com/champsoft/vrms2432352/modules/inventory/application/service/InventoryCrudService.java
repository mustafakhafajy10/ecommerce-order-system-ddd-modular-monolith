package com.champsoft.vrms2432352.modules.inventory.application.service;

import com.champsoft.vrms2432352.modules.inventory.application.exception.DuplicateInventoryItemException;
import com.champsoft.vrms2432352.modules.inventory.application.exception.InventoryItemNotFoundException;
import com.champsoft.vrms2432352.modules.inventory.application.port.out.InventoryRepositoryPort;
import com.champsoft.vrms2432352.modules.inventory.domain.model.InventoryItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryCrudService {
    private final InventoryRepositoryPort repository;

    public InventoryCrudService(InventoryRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    public InventoryItem createInventoryItem(String productId, int quantity) {
        repository.findByProductId(productId).ifPresent(existing -> {
            throw new DuplicateInventoryItemException(productId);
        });
        return repository.save(InventoryItem.create(productId, quantity));
    }

    @Transactional(readOnly = true)
    public java.util.List<InventoryItem> listInventoryItems() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public InventoryItem getInventoryItem(String id) {
        return repository.findById(id).orElseThrow(() -> new InventoryItemNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public InventoryItem getInventoryByProductId(String productId) {
        return repository.findByProductId(productId).orElseThrow(() -> new InventoryItemNotFoundException(productId));
    }

    @Transactional
    public InventoryItem updateInventoryItem(String id, int quantity) {
        var item = getInventoryItem(id);
        item.setQuantity(quantity);
        return repository.save(item);
    }

    @Transactional
    public void deleteInventoryItem(String id) {
        getInventoryItem(id);
        repository.deleteById(id);
    }

    @Transactional
    public InventoryItem reserveStock(String productId, int quantity) {
        var item = getInventoryByProductId(productId);
        item.reserve(quantity);
        return repository.save(item);
    }
}
