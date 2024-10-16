package com.rental.rental.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalPricesDTO {

    @NotNull(message = "The rental price ID is required")
    private int rentalId;

    @NotNull(message = "The price per day is required")
    private double pricePerDay;

    @NotNull(message = "The price per week is required")
    private double pricePerWeek;

    @NotNull(message = "The price per month is required")
    private double pricePerMonth;

    @NotNull(message = "The price per year is required")
    private double pricePerYear;
}
