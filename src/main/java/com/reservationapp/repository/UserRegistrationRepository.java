package com.reservationapp.repository;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.payload.UserRegistrationDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration,Long> {

    Optional<UserRegistration> findByEmail(String email);
    Optional<UserRegistration> findByUsernameOrEmail(String username, String email);
    Optional<UserRegistration> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
