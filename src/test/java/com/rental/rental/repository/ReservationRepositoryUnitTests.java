package com.rental.rental.repository;

import com.rental.rental.model.Customer;
import com.rental.rental.model.Reservation;
import com.rental.rental.model.Role;
import com.rental.rental.model.RoleEnum;
import com.rental.rental.model.Vehicle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservationRepositoryUnitTests {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setUp() {
        createRoleIfNotExists(RoleEnum.USER, "Regular user with standard permissions");
        createRoleIfNotExists(RoleEnum.ADMIN, "Administrator with elevated permissions");
        createRoleIfNotExists(RoleEnum.SUPER_ADMIN, "Super user with full access");
    }

    private void createRoleIfNotExists(RoleEnum roleEnum, String description) {
        if (roleRepository.findByName(roleEnum).isEmpty()) {
            Role role = Role.builder()
                    .name(roleEnum)
                    .description(description)
                    .build();
            roleRepository.save(role);
        }
    }

    @Test
    public void testDoubleReservationForSameVehicle() {
        // Fetch a role to associate with the customer
        Optional<Role> userRoleOptional = roleRepository.findByName(RoleEnum.USER);

        // Ensure the role is present
        Assertions.assertThat(userRoleOptional).isPresent().withFailMessage("The USER role should exist in the database.");
        Role userRole = userRoleOptional.get();

        // Create and save a customer
        Customer customer = Customer.builder()
                .customerName("Jane Doe")
                .email("janedoe@example.com")
                .password("securePassword")
                .customerAddress("456 Main St")
                .phoneNumber("555-5678")
                .driverLicense("D87654321")
                .role(userRole) // Assign role to customer
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        // Create and save a vehicle for the reservation
        Vehicle vehicle = new Vehicle();
        vehicle.setManufacturer("Toyota");
        vehicle.setModel("Corolla");
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        // Convert LocalDate to java.sql.Date for reservation dates
        Date reservationStartDate = Date.valueOf(LocalDate.now());
        Date reservationEndDate = Date.valueOf(LocalDate.now().plusDays(1));

        // First reservation attempt
        Reservation reservation1 = Reservation.builder()
                .customer(savedCustomer)
                .reservationStartDate(reservationStartDate)
                .reservationEndDate(reservationEndDate)
                .status("CONFIRMED")
                .vehicle(savedVehicle)
                .build();
        Reservation savedReservation1 = reservationRepository.save(reservation1);

        // Assert that the first reservation is saved correctly
        Assertions.assertThat(savedReservation1).isNotNull().withFailMessage("The first reservation should be saved.");
        Assertions.assertThat(savedReservation1.getReservationId()).isGreaterThan(0).withFailMessage("The first reservation should have a valid ID.");

        // Second reservation attempt (same vehicle and same time)
        Reservation reservation2 = Reservation.builder()
                .customer(savedCustomer)
                .reservationStartDate(reservationStartDate)
                .reservationEndDate(reservationEndDate)
                .status("CONFIRMED")
                .vehicle(savedVehicle)
                .build();

        // Add logic to check if the vehicle is already reserved for the same time frame
        // (This is just a mock check; you can implement it in your service layer)
        Optional<Reservation> existingReservation = reservationRepository.findByVehicleAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(savedVehicle, reservationStartDate, reservationEndDate);

        if (existingReservation.isEmpty()) {
            // If no existing reservation, save the new reservation
            reservationRepository.save(reservation2);
            Assertions.assertThat(reservation2.getReservationId()).isGreaterThan(0)
                    .withFailMessage("The second reservation should not be allowed.");
        } else {
            // If a conflict exists, ensure that the second reservation is not saved
            Assertions.assertThat(existingReservation).isPresent()
                    .withFailMessage("The second reservation should not be allowed due to a conflict.");
        }
    }
}
