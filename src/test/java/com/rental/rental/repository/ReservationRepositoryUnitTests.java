package com.rental.rental.repository;
import com.rental.rental.dto.ReservationDTO;
import com.rental.rental.model.Customer;
import com.rental.rental.model.Vehicle;
import com.rental.rental.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.*;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReservationRepositoryUnitTests {

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    private Customer customer1, customer2;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        // Set up mock data for two customers
        customer1 = new Customer();
        customer1.setCustomerName("John Doe");
        customer1.setCustomerAddress("123 Main St");
        customer1.setEmail("johndoe@example.com");
        customer1.setDriverLicense("DL12345");
        customer1.setPhoneNumber("123-456-7890");
        customer1.setPassword("password");
        customer1.setCreatedAt(new Date());
        customer1.setUpdatedAt(new Date());

        customer2 = new Customer();
        customer2.setCustomerName("Jane Smith");
        customer2.setCustomerAddress("456 Elm St");
        customer2.setEmail("janesmith@example.com");
        customer2.setDriverLicense("DL67890");
        customer2.setPhoneNumber("987-654-3210");
        customer2.setPassword("password");
        customer2.setCreatedAt(new Date());
        customer2.setUpdatedAt(new Date());

        vehicle = new Vehicle();
        vehicle.setVehicleName("Toyota Corolla");
        vehicle.setManufacturer("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setPlateNumber("ABC123");
        vehicle.setEngineCapacity(1800);
        vehicle.setFuelType("Petrol");
        vehicle.setHasAirConditioning(true);
        vehicle.setLatitude(31.7683);
        vehicle.setLongitude(35.2137);
        vehicle.setVehicleStatus("Available");
        vehicle.setVersion(1L);  // Ensure optimistic locking is set up

        // Mock the save methods
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer1, customer2); // Mock saving both customers
        when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicle);
    }


    @Test
    public void testConcurrentReservationRequests() throws InterruptedException, ExecutionException {
        Date startDate = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.now().plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Create two reservation DTOs with the same start and end date for two different customers
        ReservationDTO reservationDTO1 = ReservationDTO.builder()
                .customerId(customer1.getCustomerId())  // Different customer
                .vehicleIds(Collections.singletonList(vehicle.getVehicleId()))
                .reservationStartDate(startDate)
                .reservationEndDate(endDate)
                .status("CONFIRMED")
                .build();

        ReservationDTO reservationDTO2 = ReservationDTO.builder()
                .customerId(customer2.getCustomerId())  // Different customer
                .vehicleIds(Collections.singletonList(vehicle.getVehicleId()))
                .reservationStartDate(startDate)
                .reservationEndDate(endDate)
                .status("CONFIRMED")
                .build();

        // Mock the ReservationService to return valid DTOs when creating reservations
        when(reservationService.createReservation(reservationDTO1)).thenReturn(reservationDTO1);
        when(reservationService.createReservation(reservationDTO2)).thenReturn(reservationDTO2);

        // Create a thread pool for concurrent execution
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Define tasks for concurrent reservation requests
        Callable<ReservationDTO> reservationTask1 = () -> reservationService.createReservation(reservationDTO1);
        Callable<ReservationDTO> reservationTask2 = () -> reservationService.createReservation(reservationDTO2);

        // Submit the tasks for execution
        Future<ReservationDTO> result1 = executor.submit(reservationTask1);
        Future<ReservationDTO> result2 = executor.submit(reservationTask2);

        // Count how many reservations were successfully created
        int successCount = 0;

        try {
            ReservationDTO res1 = result1.get();
            assertNotNull(res1);  // Check that the reservation is not null
            successCount++;
        } catch (ExecutionException e) {
            // Expected exception when second reservation is not allowed due to concurrency
            System.out.println("Error for reservation 1: " + e.getCause());
        }

        try {
            ReservationDTO res2 = result2.get();
            assertNotNull(res2);  // Check that the reservation is not null
            successCount++;
        } catch (ExecutionException e) {
            // Expected exception when second reservation is not allowed due to concurrency
            System.out.println("Error for reservation 2: " + e.getCause());
        }

        // Assert that only one reservation was successfully created
        org.assertj.core.api.Assertions.assertThat(successCount).isEqualTo(1);

        executor.shutdown();
    }





}