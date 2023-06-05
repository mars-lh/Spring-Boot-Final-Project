package com.project.java.project.springboot.controller.RestFlight;


import com.project.java.project.springboot.model.custom.ErrorResponseDTO;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsRequestDTO;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.service.Flight.FlightService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {

    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping("/search")
    public ResponseEntity<Optional<List<FlightsDTOResponse>>> findFlightsByDestination(@RequestBody FlightsRequestDTO flightsRequestDTO) {
        return ResponseEntity.ok(flightService.searchFlightsByUser(flightsRequestDTO));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/new")
    ResponseEntity<?> createFlight(@RequestBody FlightsRequestDTO flightsRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(flightService.flightNumberGenerator(flightsRequestDTO)));

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    ResponseEntity<List<FlightsDTOResponse>> loadAllFlights() {
        return ResponseEntity.ok(flightService.findALlFlights());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    ResponseEntity<FlightsDTOResponse> findFlightbyID(@PathVariable(value = "id") Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(flightService.findFlightByID(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<FlightsDTOResponse> updateFlight(@PathVariable(value = "id") Long id, @RequestBody FlightsRequestDTO updateflight) {
        FlightsDTOResponse update = flightService.updateFlightDepartureDate(id, updateflight);
        return ResponseEntity.ok(update);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlightById(@PathVariable(value = "id") Long id) throws FlightNotFoundException {
        flightService.deleteFlightById(id);
        return ResponseEntity.ok("Flight deleted successfully");

    }

}




