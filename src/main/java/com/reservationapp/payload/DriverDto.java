package com.reservationapp.payload;

import com.reservationapp.entity.Bus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {

    private String name;
    private String licenseNumber;
    private String addNumber;
    private String address;
    private String contactNumber;
    private String alternateContactNumber;
    private String emailId;
}
