package com.rental.rental.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private int reservationId;

    @NotNull(message = "Reservation start date is required")
    @FutureOrPresent(message = "Reservation start date must be in the present or future")
    private Date reservationStartDate;

    @NotNull(message = "Reservation end date is required")
    @Future(message = "Reservation end date must be in the future")
    private Date reservationEndDate;

    @NotBlank(message = "Status is required")
    private String status;

    private String additionalServices;

    @NotNull(message = "Customer ID is required")
    private int customerId;

    @NotEmpty(message = "Reservation must include at least one branch")
    private List<Integer> branchIds;

    @NotEmpty(message = "Reservation must include at least one vehicle")
    private List<Integer> vehicleIds;

    @NotNull(message = "Rental ID is required")
    private int rentalId;

    @NotEmpty(message = "Reservation must include at least one service")
    private Set<Integer> serviceIds;
}
