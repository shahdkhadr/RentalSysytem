package com.rental.rental.repository;

import com.rental.rental.model.Reservation;
import com.rental.rental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.Optional;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findByVehicleAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
            Vehicle vehicle, Date startDate, Date endDate);}
