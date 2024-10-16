package com.rental.rental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TruckDTO {

    @NotNull(message = "Cargo capacity is required")
    private int cargoCapacity;

    @NotNull(message = "Number of axles is required")
    private int numOfAxles;

    @NotBlank(message = "Trailer type is required")
    private String trailerType;

}
