package com.reservationapp.repository;

import com.reservationapp.entity.SubRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRouteRepository extends JpaRepository<SubRoute,Long> {
}
