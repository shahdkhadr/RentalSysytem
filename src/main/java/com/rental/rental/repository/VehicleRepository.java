package com.rental.rental.repository;

import com.rental.rental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Vehicle> findByVehicleId(int vehicleId);
}
