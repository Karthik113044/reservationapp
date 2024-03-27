package com.reservationapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusDto {
    private Long busId;
    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;
    private RouteDto route;
    private List<SubRouteDto> subRoutes;

}