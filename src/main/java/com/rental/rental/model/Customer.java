package com.rental.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;
    private String customerAddress;
    private String phoneNumber;
    private String driverLicense;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Notification> notifications;
    @ManyToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;

}
