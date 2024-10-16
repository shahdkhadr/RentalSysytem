package com.rental.rental.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDTO {

    @NotNull(message = "The rental ID is required")
    private int rentalId;

    @NotEmpty(message = "The driver license is required")
    private String driverLicense;

    @NotNull(message = "The rental start date is required")
    private Date rentalStartDate;

    @NotNull(message = "The rental end date is required")
    private Date rentalEndDate;

    @NotNull(message = "Reservation ID is required")
    private int reservationId;

    private List<Integer> vehicleIds;

    @NotNull(message = "Rental prices ID is required")
    private int rentalPricesId;

    @NotNull(message = "Payment ID is required")
    private int paymentId;
}
