package com.reservationapp.payload;

import com.reservationapp.entity.Route;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RouteDetailsDto {

    private List<Route> route;
    private List<SubRouteDto> subRouteDto;
}
