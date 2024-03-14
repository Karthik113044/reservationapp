package com.reservationapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusDto {
    private Long busId;
    private String busNumber;
    private String busType;
    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;
    private double price;
    private int totalSeats;
    private int availableSeats;
}
