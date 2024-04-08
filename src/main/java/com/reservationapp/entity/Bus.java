package com.reservationapp.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name ="bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @Column(name="bus_number", unique = true)
    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;

//    @OneToOne(mappedBy = "bus")
//    private Route route;


   // private List<SubRoute> subRoutes;
}
