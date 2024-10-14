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
public class ParkingStall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stallId;

    @ManyToOne
    @JoinColumn(name = "branchId", nullable = false)
    private Branch branch;

    @OneToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
}
