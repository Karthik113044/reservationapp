package com.reservationapp.service;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.payload.UserRegistrationDto;

public interface UserService {

    public UserRegistrationDto createUser(UserRegistration userRegistration);

    public UserRegistration getUserbyId(long id);
}
