package com.rental.rental.repository;

import com.rental.rental.model.Branch;
import com.rental.rental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
