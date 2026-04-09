package com.champsoft.vrms2432352.modules.inventory.api;

import com.champsoft.vrms2432352.modules.inventory.api.dto.CreateInventoryItemRequest;
import com.champsoft.vrms2432352.modules.inventory.api.dto.InventoryItemResponse;
import com.champsoft.vrms2432352.modules.inventory.api.dto.UpdateInventoryItemRequest;
import com.champsoft.vrms2432352.modules.inventory.application.service.InventoryCrudService;
import com.champsoft.vrms2432352.modules.inventory.domain.model.InventoryItem;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory-items")
public class InventoryController {
    private final InventoryCrudService inventoryCrudService;

    public InventoryController(InventoryCrudService inventoryCrudService) {
        this.inventoryCrudService = inventoryCrudService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryItemResponse>> list() {
        return ResponseEntity.ok(inventoryCrudService.listInventoryItems().stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(toResponse(inventoryCrudService.getInventoryItem(id)));
    }

    @PostMapping
    public ResponseEntity<InventoryItemResponse> create(@Valid @RequestBody CreateInventoryItemRequest request) {
        var item = inventoryCrudService.createInventoryItem(request.productId(), request.quantity());
        return ResponseEntity.created(URI.create("/api/inventory-items/" + item.getId())).body(toResponse(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemResponse> update(
        @PathVariable String id,
        @Valid @RequestBody UpdateInventoryItemRequest request
    ) {
        return ResponseEntity.ok(toResponse(inventoryCrudService.updateInventoryItem(id, request.quantity())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        inventoryCrudService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }

    private InventoryItemResponse toResponse(InventoryItem inventoryItem) {
        return new InventoryItemResponse(inventoryItem.getId(), inventoryItem.getProductId(), inventoryItem.getQuantity());
    }
}
