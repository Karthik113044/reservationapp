package com.reservationapp.controller;

import com.reservationapp.entity.Route;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.RouteDto;
import com.reservationapp.payload.SubRouteDto;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus/v1/api")
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private RouteRepository routeRepository;

    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusDto busDto){



    try{
        BusDto busDto1 = busService.createBus(busDto);

        return new ResponseEntity<>( "Bus registered successfully" +"BUS REGISTERED", HttpStatus.CREATED);
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

    @GetMapping("/route")
    public List<Route> searchBusses(
            @RequestParam("fromLocation") String fromLocation,
            @RequestParam("toLocation") String toLocation,
            @RequestParam("fromDate") String fromDate

    ){

      return routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);

    }
}
