package com.rental.rental.model;

import com.rental.rental.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRUCK")
public class Truck extends Vehicle {
    private int cargoCapacity;
    private int numOfAxles;
    private String trailerType;
}
