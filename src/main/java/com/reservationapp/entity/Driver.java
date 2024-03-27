package com.reservationapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @Column(name = "driver_name")
    private String name;

    @Column(name = "driver_license_number")
    private String licenseNumber;

    @Column(name = "add_number")
    private String addNumber;

    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "alternate_contact_number")
    private String alternateContactNumber;

    @Column(name = "email_id")
    private String emailId;

    // Constructors, getters, and setters
}

