package com.project.java.project.springboot.service.userService;


import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTOResponse> findAll ();

    UserDTOResponse registerUserDTO (UserDTORequest userDTO);

    UserDTOResponse registerUserDTODetails (UserDTORequest userDTO) throws FlightNotFoundException;

    Optional<UserDTOResponse> bookFlight (Long id, UserDTORequest userDTO) throws FlightNotFoundException;




}
