package com.rental.rental.repository;

import com.rental.rental.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByCustomer_CustomerId(int customerId);
}
