package com.reservationapp.repository;

import com.reservationapp.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface RouteRepository extends JpaRepository<Route, Long> {
   List<Route> findByFromLocationAndToLocationAndFromDate(String fromLocation, String toLocation, String fromDate);


}

