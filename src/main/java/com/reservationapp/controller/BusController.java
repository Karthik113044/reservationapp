package com.reservationapp.controller;

import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.RouteDetailsDto;
import com.reservationapp.payload.SubRouteDto;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.service.BusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/bus/v1/api")
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

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
    public ResponseEntity<?> searchBuses(
            @RequestParam("fromLocation") String fromLocation,
            @RequestParam("toLocation") String toLocation,
            @RequestParam("fromDate") String fromDate
    ) {
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);

        List<RouteDetailsDto> routeDetails = new ArrayList<>();

        for (Route route : routes) {
            List<SubRoute> subRoutes = subRouteRepository.findByRouteId(route.getId());
            if (!subRoutes.isEmpty()) {
                List<SubRouteDto> subRouteDtos = new ArrayList<>();
                for (SubRoute subRoute : subRoutes) {
                    SubRouteDto subRouteDto = new ModelMapper().map(subRoute, SubRouteDto.class);
                    subRouteDtos.add(subRouteDto);
                }
                RouteDetailsDto routeDetailsDto = new RouteDetailsDto();
                routeDetailsDto.setRoute(Collections.singletonList(route));
                routeDetailsDto.setSubRouteDto(subRouteDtos);
                routeDetails.add(routeDetailsDto);
            } else {
                return new ResponseEntity<>("No subroutes found for the given route ID", HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(routeDetails, HttpStatus.OK);
    }
    }