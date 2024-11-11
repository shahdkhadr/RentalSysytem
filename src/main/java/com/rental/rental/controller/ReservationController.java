package com.rental.rental.controller;

import com.rental.rental.dto.ReservationDTO;
import com.rental.rental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-rental-system")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/reservation")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReservationDTO> getReservationById(@RequestParam int reservationId) {
        ReservationDTO reservation = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/reservation")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/reservation")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReservationDTO> updateReservation(
            @RequestParam int reservationId,
            @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationId, reservationDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/reservation")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<String> deleteReservation(@RequestParam int reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Reservation with ID " + reservationId + " has been deleted successfully.");
    }
}
