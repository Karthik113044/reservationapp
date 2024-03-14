package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.payload.BusDto;
import com.reservationapp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusService implements BusServiceImpl {

    @Autowired
    private BusRepository busRepository;

    @Override
    public BusDto createBus(BusDto busDto) {
        Bus bus = mapToEntity(busDto);
        Bus save = busRepository.save(bus);

        return mapToDto(save);

    }

    public BusDto getBusById(Long busId) {
        Bus bus1 = busRepository.findById(busId).get();

        return mapToDto(bus1);

    }
    
    Bus mapToEntity (BusDto busDto){

        Bus bus = new Bus();

        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setFromLocation(busDto.getFromLocation());
        bus.setToLocation(busDto.getToLocation());
        bus.setFromDate(busDto.getFromDate());
        bus.setToDate(busDto.getToDate());
        bus.setTotalDuration(busDto.getTotalDuration());
        bus.setFromTime(busDto.getFromTime());
        bus.setToTime(busDto.getToTime());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAvailableSeats(busDto.getAvailableSeats());
        
        return bus;
    }

    BusDto mapToDto (Bus save){

        BusDto busDto = new BusDto();

        busDto.setBusId(save.getBusId());
        busDto.setBusNumber(save.getBusNumber());
        busDto.setBusType(save.getBusType());
        busDto.setFromLocation(save.getFromLocation());
        busDto.setToLocation(save.getToLocation());
        busDto.setFromDate(save.getFromDate());
        busDto.setToDate(save.getToDate());
        busDto.setTotalDuration(save.getTotalDuration());
        busDto.setFromTime(save.getFromTime());
        busDto.setToTime(save.getToTime());
        busDto.setPrice(save.getPrice());
        busDto.setTotalSeats(save.getTotalSeats());
        busDto.setAvailableSeats(save.getAvailableSeats());

        return busDto;
    }
}
