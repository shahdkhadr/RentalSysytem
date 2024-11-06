package com.rental.rental.repository;

import com.rental.rental.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
