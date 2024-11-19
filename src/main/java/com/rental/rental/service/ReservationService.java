package com.rental.rental.service;
import com.rental.rental.dto.ReservationDTO;
import com.rental.rental.model.*;
import com.rental.rental.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        return convertToDTO(reservation);
    }
    @Transactional
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        try {
            System.out.println("Converting DTO to Reservation entity.");
            Reservation reservation = convertToEntity(reservationDTO);

            System.out.println("Fetching vehicle with lock.");
            Vehicle lockedVehicle = vehicleRepository.findByVehicleIdWithLock(reservation.getVehicle().getVehicleId())
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

            System.out.println("Checking if the vehicle is already reserved.");
            if (reservationRepository.existsByVehicleAndStatus(lockedVehicle, "CONFIRMED")) {
                System.out.println("Vehicle already reserved.");
                throw new IllegalStateException("Vehicle already reserved.");
            }

            System.out.println("Saving the reservation.");
            Reservation savedReservation = reservationRepository.save(reservation);

            System.out.println("Reservation saved successfully. ID: " + savedReservation.getReservationId());
            return convertToDTO(savedReservation);

        } catch (Exception e) {
            System.out.println("Error in createReservation: " + e.getMessage());
            throw e;
        }
    }



    public void deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        reservationRepository.delete(reservation);
    }

    public ReservationDTO updateReservation(int id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        existingReservation.setReservationStartDate(reservationDTO.getReservationStartDate());
        existingReservation.setReservationEndDate(reservationDTO.getReservationEndDate());
        existingReservation.setStatus(reservationDTO.getStatus());
        existingReservation.setAdditionalServices(reservationDTO.getAdditionalServices());

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return convertToDTO(updatedReservation);
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO.ReservationDTOBuilder builder = ReservationDTO.builder()
                .reservationId(reservation.getReservationId())
                .reservationStartDate(reservation.getReservationStartDate())
                .reservationEndDate(reservation.getReservationEndDate())
                .status(reservation.getStatus())
                .additionalServices(reservation.getAdditionalServices())
                .customerId(reservation.getCustomer().getCustomerId())
                .branchIds(reservation.getBranches().stream()
                        .map(branch -> branch.getBranchId())
                        .collect(Collectors.toList()))
                .vehicleIds(Collections.singletonList(reservation.getVehicle().getVehicleId()))
                .serviceIds(reservation.getServices().stream()
                        .map(service -> service.getServiceId())
                        .collect(Collectors.toSet()));

        // Check if the rental exists before adding rentalId to the DTO
        if (reservation.getRental() != null) {
            builder.rentalId(reservation.getRental().getRentalId());
        }

        return builder.build();
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .reservationId(reservationDTO.getReservationId())
                .reservationStartDate(reservationDTO.getReservationStartDate())
                .reservationEndDate(reservationDTO.getReservationEndDate())
                .status(reservationDTO.getStatus())
                .additionalServices(reservationDTO.getAdditionalServices())
                .build();

        // Set Customer
        Customer customer = customerRepository.findById(reservationDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + reservationDTO.getCustomerId()));
        reservation.setCustomer(customer);

        // Set Branches
        List<Branch> branches = branchRepository.findAllById(reservationDTO.getBranchIds());
        reservation.setBranches(branches);

        // Set Vehicles
        List<Vehicle> vehicles = vehicleRepository.findAllById(reservationDTO.getVehicleIds());  // Getting the vehicle(s)
        if (!vehicles.isEmpty()) {
            reservation.setVehicle(vehicles.get(0));
        }
        // Set Services
        Set<_Service> services = serviceRepository.findAllById(reservationDTO.getServiceIds())
                .stream().collect(Collectors.toSet());
        reservation.setServices(services);


        return reservation;
    }
}
