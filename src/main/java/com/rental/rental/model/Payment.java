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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private String paymentMethod;
    private Date paymentDate;
    private String status;
    private double totalAmount;

    @OneToOne(mappedBy = "payment")
    private Invoice invoice;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Branch> branches;

    @OneToOne
    @JoinColumn(name = "rentalId", referencedColumnName = "rentalId")
    private Rental rental;
}
