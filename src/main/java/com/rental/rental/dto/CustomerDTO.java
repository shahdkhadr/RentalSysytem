package com.rental.rental.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    @NotNull(message = "The customer ID is required")
    private int customerId;

    @NotEmpty(message = "The customer name is required")
    private String customerName;

    @NotEmpty(message = "The customer address is required")
    private String customerAddress;

    @NotEmpty(message = "The customer phone number is required")
    private String phoneNumber;

    @NotEmpty(message = "The customer driver license is required")
    private String driverLicense;

    @NotEmpty(message = "The customer must have at least one notification")
    private List<Integer> notificationIds;

    @NotEmpty(message = "The customer must have at least one reservation")
    private List<Integer> reservationIds;
}
