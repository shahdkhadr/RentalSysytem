package com.rental.rental.model;

import com.rental.rental.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUS")
public class Bus extends Vehicle {
    private int numOfSeats;
    private boolean hasWiFi;
}
