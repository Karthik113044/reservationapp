package com.reservationapp.controller;

import com.reservationapp.payload.BusDto;
import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/bus/api")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusDto busDto){


    try{

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate =  formatter.parse(busDto.getFromDate());
        Date toDate = formatter.parse(busDto.getToDate());
        BusDto busDto1 = busService.createBus(busDto);

        return new ResponseEntity<>( "Bus registered successfully" + busDto1, HttpStatus.CREATED);
    }

    catch(Exception e)

    {
        return new ResponseEntity<>("Failed to register Bus: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("/{busId}")
    public ResponseEntity<?> getBusById(@PathVariable long busId){

        BusDto busById = busService.getBusById(busId);
        return new ResponseEntity<>(busById,HttpStatus.OK);

    }
}
