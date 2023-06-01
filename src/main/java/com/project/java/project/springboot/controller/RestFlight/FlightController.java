package com.project.java.project.springboot.controller.RestFlight;


import com.project.java.project.springboot.model.custom.ErrorResponseDTO;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.service.Flight.FlightService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {

    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


//    @PostMapping("/create/Flight")
//    ResponseEntity<?> createFlight (@RequestBody FlightsDTORequest flightsDTORequest) {
//        String airlineCode = flightsDTORequest.getAirline_code().toString().toUpperCase(); // Convert the input value to uppercase
//
//        boolean isValidAirline = Arrays.stream(AirlinesEnum.values())
//                .map(Enum::name)
//                .anyMatch(airline -> airline.equals(airlineCode));
//
//        if (isValidAirline) {
//            return ResponseEntity.ok(flightService.createFlight(flightsDTORequest));
//        } else {
//            String errorMessage = "Invalid airline code.";
//            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorMessage);
//            return ResponseEntity.badRequest().body(errorResponseDTO);
//        }
//        }

    @PostMapping("/create/Flight1")
    ResponseEntity<?> createFlight1 (@RequestBody FlightsDTORequest flightsDTORequest) {
        String airlineCode = flightsDTORequest.getAirline_code().toString().toUpperCase(); // Convert the input value to uppercase

        boolean isValidAirline = Arrays.stream(AirlinesEnum.values())
                .map(Enum::name)
                .anyMatch(airline -> airline.equals(airlineCode));

        if (isValidAirline) {
//            flightService.flightNumberGenerator(flightsDTORequest);
            String created = "CreatedSuccesfull";
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(created);
            return ResponseEntity.ok().body(flightService.createFlight(flightService.flightNumberGenerator(flightsDTORequest)));
        } else {
            String errorMessage = "Invalid airline code.";
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorMessage);
            return ResponseEntity.badRequest().body(errorResponseDTO);
        }
    }

    @GetMapping("/flights")
    ResponseEntity<List<FlightsDTOResponse>> loadAllFlights () {
        return ResponseEntity.ok(flightService.findALlFlights());
    }

    @GetMapping("/flights/{id}")
    ResponseEntity <FlightsDTOResponse> findFlightbyID (@PathVariable (value = "id") Long id) throws ChangeSetPersister.NotFoundException {
        FlightsDTOResponse existingFlight = flightService.findFlightByID(id);
        if (!existingFlight.getId().equals(id)) {
            return (ResponseEntity<FlightsDTOResponse>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(flightService.findFlightByID(id));
    }

    @PutMapping("/updateFlight/{id}")
    public ResponseEntity <FlightsDTOResponse> updateFlight(@PathVariable (value = "id") Long id, @RequestBody FlightsDTORequest updateflight) throws ChangeSetPersister.NotFoundException {
        FlightsEntity eligibleFlight = flightService.flightEntityforDate(id);
        Date flightActualDate = eligibleFlight.getDepartureDate();
        if (updateflight == null) {
            return ResponseEntity.notFound().build();
        }else if (eligibleFlight.getFlightStatus().equals(FlightStatusEnum.BOOKED) && updateflight.getDepartureDate().after(flightActualDate) && eligibleFlight.getArrivalDate().after(updateflight.getDepartureDate())) {
          FlightsDTOResponse update =  flightService.updateFlightDepartureDate(id, updateflight);
            return ResponseEntity.ok(update);
        } else if (!eligibleFlight.getFlightStatus().equals(FlightStatusEnum.BOOKED)) {
           Optional<FlightsDTOResponse> updateEverything = flightService.updateFlightDetails(id, updateflight);
           FlightsDTOResponse UpdateToDTO = updateEverything.get();
           return ResponseEntity.ok(UpdateToDTO);
        }
          return ResponseEntity.ok(null);
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<String> deleteFlightById(@PathVariable (value = "id") Long id) throws ChangeSetPersister.NotFoundException {
        FlightsDTOResponse getById = flightService.findFlightByID(id);
        if (getById.getFlightStatus().equals(null)) {
            return ResponseEntity.notFound().build();
        }else if (!flightService.findFlightByID(id).getFlightStatus().equals(FlightStatusEnum.BOOKED)) {
        flightService.deleteFlightById(id);
        return ResponseEntity.ok("Flight deleted successfully");
        } else {
            return ResponseEntity.ok("Flight could not be deleted because is already booked");
        }
    }

    }




