package com.project.java.project.springboot.service.userService;


import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.flights.FlightsRequestDTO;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserResponseDTO registerUserDTODetails (UserRequestDTO userDTO) throws FlightNotFoundException;

    Optional<UserResponseDTO> bookFlight (Long id, UserRequestDTO userDTO) throws FlightNotFoundException;

    UserDetailDTOResponse findAllBookingsForTraveller(Long id);


}
