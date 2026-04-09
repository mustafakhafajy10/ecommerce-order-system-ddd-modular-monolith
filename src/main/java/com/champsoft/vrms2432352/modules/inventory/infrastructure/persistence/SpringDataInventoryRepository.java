package com.champsoft.vrms2432352.modules.inventory.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataInventoryRepository extends JpaRepository<InventoryItemJpaEntity, String> {
    Optional<InventoryItemJpaEntity> findByProductId(String productId);
}
