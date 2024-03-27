package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.RouteDto;
import com.reservationapp.payload.SubRouteDto;

public interface BusServiceImpl {
    public BusDto createBus(BusDto busDto);

    public BusDto getBusById(Long busId);



}
