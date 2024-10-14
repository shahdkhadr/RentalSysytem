package com.rental.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @Temporal(TemporalType.DATE)
    private Date reservationStartDate;

    @Temporal(TemporalType.DATE)
    private Date reservationEndDate;

    private String status;
    private String additionalServices;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Customer> customers;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Branch> branches;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Rental rental;

    @ManyToMany
    @JoinTable(
            name = "reservation_service",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services;
}