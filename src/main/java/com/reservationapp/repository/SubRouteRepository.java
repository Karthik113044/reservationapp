package com.reservationapp.repository;

import com.reservationapp.entity.SubRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubRouteRepository extends JpaRepository<SubRoute,Long> {
    List<SubRoute> findByRouteId(Long routeId);
}