package com.rental.rental.repository;

import com.rental.rental.model._Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<_Service, Integer> {
}
