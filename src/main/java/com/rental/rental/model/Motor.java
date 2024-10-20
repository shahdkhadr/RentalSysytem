package com.rental.rental.model;

import com.rental.rental.model.Vehicle;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTOR")
public class Motor extends Vehicle {
    private boolean hasSideCar;
    private String motorType;
}
