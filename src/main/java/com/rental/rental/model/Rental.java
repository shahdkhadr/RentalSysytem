package com.rental.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    private String driverLicense;

    @Temporal(TemporalType.DATE)
    private Date rentalStartDate;

    @Temporal(TemporalType.DATE)
    private Date rentalEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservationId")
    private Reservation reservation;
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentalPricesId", referencedColumnName = "rentalId")
    private RentalPrices rentalPrices;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId", referencedColumnName = "paymentId")
    private Payment payment;
}
