package com.rental.rental.repository;

import com.rental.rental.dto.ReservationDTO;
import com.rental.rental.model.Customer;
import com.rental.rental.model.Vehicle;
import com.rental.rental.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.*;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReservationRepositoryUnitTests {

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private ReservationService reservationService;

    private Customer customer1, customer2;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setCustomerName("John Doe");

        customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setCustomerName("Jane Smith");

        vehicle = new Vehicle();
        vehicle.setVehicleId(1);
        vehicle.setVehicleName("Toyota Corolla");

        when(vehicleRepository.findByVehicleIdWithLock(1)).thenReturn(java.util.Optional.of(vehicle));
        when(reservationRepository.existsByVehicleAndStatus(vehicle, "CONFIRMED"))
                .thenReturn(false)
                .thenReturn(true);
    }

    @Test
    public void testConcurrentReservationRequests() throws InterruptedException, ExecutionException {
        Date startDate = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(LocalDate.now().plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

        ReservationDTO reservationDTO1 = ReservationDTO.builder()
                .customerId(customer1.getCustomerId())
                .vehicleIds(Collections.singletonList(vehicle.getVehicleId()))
                .reservationStartDate(startDate)
                .reservationEndDate(endDate)
                .status("CONFIRMED")
                .build();

        ReservationDTO reservationDTO2 = ReservationDTO.builder()
                .customerId(customer2.getCustomerId())
                .vehicleIds(Collections.singletonList(vehicle.getVehicleId()))
                .reservationStartDate(startDate)
                .reservationEndDate(endDate)
                .status("CONFIRMED")
                .build();

        // Mock the service behavior for concurrent reservations
        when(reservationService.createReservation(reservationDTO1)).thenReturn(reservationDTO1);
        when(reservationService.createReservation(reservationDTO2)).thenThrow(new IllegalStateException("Vehicle already reserved."));

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Define tasks
        Callable<ReservationDTO> task1 = () -> reservationService.createReservation(reservationDTO1);
        Callable<ReservationDTO> task2 = () -> reservationService.createReservation(reservationDTO2);

        // Submit tasks
        Future<ReservationDTO> result1 = executor.submit(task1);
        Future<ReservationDTO> result2 = executor.submit(task2);

        int successCount = 0;

        try {
            ReservationDTO res1 = result1.get();
            assertNotNull(res1);
            successCount++;
            System.out.println("Reservation for Customer 1 (John Doe) succeeded.");
        } catch (ExecutionException e) {
            System.out.println("Reservation for Customer 1 (John Doe) failed: " + e.getCause());
        }

        try {
            result2.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof IllegalStateException);
            System.out.println("Reservation for Customer 2 (Jane Smith) failed: " + e.getCause());
        }

        // Assert that only one reservation was successful
        System.out.println("Total successful reservations: " + successCount);
        assertTrue(successCount == 1);

        executor.shutdown();
    }

}



