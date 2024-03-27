package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SubRouteDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@Service
public class BusService implements BusServiceImpl {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Transactional
    public BusDto createBus(BusDto busDto) {

        Route route = new Route();

        route.setFromLocation(busDto.getRoute().getFromLocation());
        route.setToLocation(busDto.getRoute().getToLocation());
        route.setFromDate(busDto.getRoute().getFromDate());
        route.setToDate(busDto.getRoute().getToDate());
        route.setTotalDuration(busDto.getRoute().getTotalDuration());
        route.setFromTime(busDto.getRoute().getFromTime());
        route.setToTime(busDto.getRoute().getToTime());


        Route savedRoute = routeRepository.save(route);

        Bus bus = new Bus();

        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAvailableSeats(busDto.getAvailableSeats());

        //bus.setRoute(route);

        Bus savedBus = busRepository.save(bus);

        Route routeUpdate = routeRepository.findById(savedRoute.getId()).get();

        routeUpdate.setBus(savedBus);
        routeRepository.save(routeUpdate);


        if(busDto.getSubRoutes() != null && !busDto.getSubRoutes().isEmpty()) {

            for ( SubRouteDto subRouteDto : busDto.getSubRoutes()) {

                SubRoute subRoute = new SubRoute();

                subRoute.setFromLocation(subRouteDto.getFromLocation());
                subRoute.setToLocation(subRouteDto.getToLocation());
                subRoute.setFromDate(subRouteDto.getFromDate());
                subRoute.setToDate(subRouteDto.getToDate());
                subRoute.setTotalDuration(subRouteDto.getTotalDuration());
                subRoute.setFromTime(subRouteDto.getFromTime());
                subRoute.setToTime(subRouteDto.getToTime());

                subRoute.setRoute(savedRoute);

                subRouteRepository.save(subRoute);
            }
        }
        return null;
    }

    public BusDto getBusById(Long busId) {
        Bus bus = busRepository.findById(busId).orElse(null);
        return mapToDto(bus);
    }
    private BusDto mapToDto(Bus bus) {

       BusDto busDto = modelMapper().map(bus, BusDto.class);

        return busDto;
    }

}
