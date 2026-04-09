package com.champsoft.vrms2432352.modules.customer.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, String> {
    Optional<CustomerJpaEntity> findByEmail(String email);
}
