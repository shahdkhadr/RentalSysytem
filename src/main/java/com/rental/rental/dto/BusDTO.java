package com.rental.rental.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BusDTO {
    @NotEmpty( message = "The number of seats is required")
    private int numOfSeats;

    @NotEmpty( message = "The availability of Wifi is required")
    private boolean hasWiFi;
}
