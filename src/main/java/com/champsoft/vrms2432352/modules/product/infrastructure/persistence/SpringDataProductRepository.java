package com.champsoft.vrms2432352.modules.product.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, String> {
    Optional<ProductJpaEntity> findByNameIgnoreCase(String name);
}
