package com.champsoft.vrms2432352.modules.customer.application.port.out;

import com.champsoft.vrms2432352.modules.customer.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(String id);
    Optional<Customer> findByEmail(String email);
    void deleteById(String id);
    boolean existsById(String id);
}
