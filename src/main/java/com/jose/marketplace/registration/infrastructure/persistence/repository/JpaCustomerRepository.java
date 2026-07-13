package com.jose.marketplace.registration.infrastructure.persistence.repository;



import com.jose.marketplace.registration.domain.Customer;
import com.jose.marketplace.registration.domain.CustomerRepository;
import com.jose.marketplace.registration.domain.CustumerId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    private CustomerEntityRepository customerEntityRepository;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        var iterable = customerEntityRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaCustomerRepository::mapper)
                .toList();
    }

    private static com.jose.marketplace.registration.infrastructure.persistence.entity.Customer mapper(Customer customer) {
        var entity = new com.jose.marketplace.registration.infrastructure.persistence.entity.Customer();

        entity.setId(customer.getId().id());
        entity.setFirstName(customer.getName());
        entity.setEmail(customer.getEmail());

        return entity;
    }



    private static Customer mapper(com.jose.marketplace.registration.infrastructure.persistence.entity.Customer entity) {
        String fullName = Optional.ofNullable(entity.getLastName())
                .map(lastName -> entity.getFirstName() + " " + lastName)
                .orElseGet(entity::getFirstName);

        return new Customer(new CustumerId(entity.getId()), fullName, entity.getEmail());
    }

}
