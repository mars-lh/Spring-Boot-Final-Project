package com.project.java.project.springboot.controller.RestUser;

import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.service.Flight.FlightService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import com.project.java.project.springboot.service.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PreAuthorize("hasAuthority('ROLE_TRAVELLER')")
    @GetMapping("/user")
    public ResponseEntity<List<UserDTOResponse>> laodAllUsers () {
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_TRAVELLER')")
    @PostMapping("/findFlight")
    public ResponseEntity<Optional<List<FlightsDTOResponse>>> findFlightsByDestination (@RequestBody FlightsDTORequest flightsDTORequest) {
        return ResponseEntity.ok(userService.findFlights(flightsDTORequest));
    }

    @PostMapping("/new")
    public ResponseEntity<UserDTOResponse> saveUser (@RequestBody UserDTORequest userdto) throws FlightNotFoundException {
        return ResponseEntity.ok().body(userService.registerUserDTODetails(userdto));
    }


    @PutMapping("/bookflight/{userid}")
    public ResponseEntity<Optional<UserDTOResponse>> bookFlight (@PathVariable(value = "userid")Long id,  @RequestBody UserDTORequest userDTORequest) throws FlightNotFoundException {
             Optional<UserDTOResponse> update = userService.bookFlight(id, userDTORequest);
        return ResponseEntity.ok(update);
    }





}
