package com.rental.rental.model;

import com.rental.rental.dto.VehicleDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
public class Vehicle {
    @Version
    private Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;

    private String vehicleName;
    private String vehicleStatus;
    private String fuelType;
    private double engineCapacity;
    private boolean hasAirConditioning;
    private String model;
    private String manufacturer;
    private String plateNumber;
    private double longitude;
    private double latitude;
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private VehicleType dtype;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleCheck> vehicleChecks;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private ParkingStall parkingStall;

    @ManyToOne
    @JoinColumn(name = "rentalId")
    private Rental rental;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    public enum VehicleType {
        MOTOR,
        CAR,
        TRUCK,
        VAN,
        BUS
    }
}
