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
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private String paymentMethod;
    private Date paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private double totalAmount;
    private String cardType;

    @OneToOne(mappedBy = "payment")
    private Invoice invoice;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Branch> branches;

}
