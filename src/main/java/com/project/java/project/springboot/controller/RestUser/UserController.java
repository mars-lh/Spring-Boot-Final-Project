package com.project.java.project.springboot.controller.RestUser;

import com.project.java.project.springboot.model.custom.CustomResponse;
import com.project.java.project.springboot.model.custom.ErrorResponseDTO;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import com.project.java.project.springboot.service.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }


    @PostMapping("/new")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userdto) throws FlightNotFoundException {
        return ResponseEntity.ok().body(userService.registerUserDTODetails(userdto));
    }


//    @PutMapping("/bookflight/{userid}")
//    public ResponseEntity<?> bookFlight(@PathVariable(value = "userid") Long id, @RequestBody UserRequestDTO userRequestDTO) throws FlightNotFoundException {
//        userService.bookFlight(id, userRequestDTO);
//        List<String> flightNumbers = userRequestDTO.getBookingRequestDTO().stream()
//                .map(bookingRequest -> bookingRequest.getFlights().getFlightNumber())
//                .collect(Collectors.toList());
//
//        String successfullyBooked = "Flights: " + String.join(", ", flightNumbers) + " have been successfully booked.";
//        CustomResponse booked = new CustomResponse(successfullyBooked);
//        return ResponseEntity.badRequest().body(booked);
//
//
//    }

    @GetMapping("/traveller/bookings/{id}")
    public ResponseEntity<UserDetailDTOResponse> findTravellersBookings (@PathVariable(value = "id") Long userId) {
        return ResponseEntity.ok(userService.findAllBookingsForTraveller(userId));
    }


}
