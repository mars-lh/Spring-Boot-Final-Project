package com.project.java.project.springboot.controller.RestFlight;


import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.repository.FlightRepository;
import com.project.java.project.springboot.service.Flight.FlightService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {

    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping("/create/Flight")
    ResponseEntity<FlightsDTOResponse> createFlight (@RequestBody FlightsDTORequest flightsDTORequest) {
        return ResponseEntity.ok(flightService.createFlight(flightsDTORequest));
    }

    @GetMapping("/flights")
    ResponseEntity<List<FlightsDTOResponse>> loadAllFlights () {
        return ResponseEntity.ok(flightService.findALlFlights());
    }

    @GetMapping("/flights/{id}")
    ResponseEntity<Optional<FlightsDTOResponse>> findFlightbyID (@PathVariable (value = "id") Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(flightService.findFlightByID(id));
    }


    @DeleteMapping("/flight/{id}")
    public ResponseEntity<String> deleteFlightById(@PathVariable (value = "id") Long id) throws ChangeSetPersister.NotFoundException {
        Optional<FlightsDTOResponse> getById = flightService.findFlightByID(id);
        if (getById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        flightService.deleteFlightById(id);
        return ResponseEntity.ok("Resource deleted successfully");
    }


    }




