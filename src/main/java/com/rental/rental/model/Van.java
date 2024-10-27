package com.rental.rental.model;

import com.rental.rental.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VAN")
public class Van extends Vehicle {
    private int numOfSeats;
}
