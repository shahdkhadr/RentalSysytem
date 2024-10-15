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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String employeeName;
    private String phoneNumber;
    private String employmentType;

    @ManyToOne
    @JoinColumn(name = "departmentId", nullable = false)
    private Department departments;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Invoice> invoices;
}
