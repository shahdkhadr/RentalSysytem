package com.rental.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double pricePerYear;

    @OneToOne(mappedBy = "rentalPrices", cascade = CascadeType.ALL)
    private Rental rental;
}
