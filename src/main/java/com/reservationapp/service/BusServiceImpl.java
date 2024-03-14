package com.reservationapp.service;

import com.reservationapp.payload.BusDto;

public interface BusServiceImpl {
    public BusDto createBus(BusDto busDto);

    public BusDto getBusById(Long busId);


}
