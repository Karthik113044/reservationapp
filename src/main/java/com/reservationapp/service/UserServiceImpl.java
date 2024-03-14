package com.reservationapp.service;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.payload.UserRegistrationDto;
import com.reservationapp.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public UserRegistrationDto createUser(UserRegistration userRegistration){

        UserRegistration save = userRegistrationRepository.save(userRegistration);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        userRegistrationDto.setName(save.getName());
        userRegistrationDto.setId(save.getId());
        userRegistrationDto.setEmail(save.getEmail());
        return userRegistrationDto;
    }

    public UserRegistration getUserbyId(long id) {
       return  userRegistrationRepository.findById(id).get();
    }
}
