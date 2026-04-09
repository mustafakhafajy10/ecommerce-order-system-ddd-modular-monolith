package com.champsoft.vrms2432352.modules.customer.infrastructure.persistence;

import com.champsoft.vrms2432352.modules.customer.application.port.out.CustomerRepositoryPort;
import com.champsoft.vrms2432352.modules.customer.domain.model.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final SpringDataCustomerRepository repository;

    public JpaCustomerRepositoryAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = new CustomerJpaEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        var saved = repository.save(entity);
        return Customer.rehydrate(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream()
            .map(entity -> Customer.rehydrate(entity.getId(), entity.getName(), entity.getEmail()))
            .toList();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return repository.findById(id)
            .map(entity -> Customer.rehydrate(entity.getId(), entity.getName(), entity.getEmail()));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email)
            .map(entity -> Customer.rehydrate(entity.getId(), entity.getName(), entity.getEmail()));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
