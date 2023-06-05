package com.project.java.project.springboot.service.Flight;

import com.project.java.project.springboot.model.flights.FlightsRequestDTO;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FlightService {

    FlightsDTOResponse createFlight(FlightsRequestDTO flightsRequestDTO);

    FlightsRequestDTO flightNumberGenerator(FlightsRequestDTO flightsRequestDTO);

    FlightsDTOResponse findFlightByID(Long id);

    FlightsEntity findFlightEntity(Long id);

    List<FlightsDTOResponse> findALlFlights();

    Optional<List<FlightsDTOResponse>> searchFlightsByUser(FlightsRequestDTO flightsRequestDTO);

    void deleteFlightById(Long id);

    Optional<FlightsDTOResponse> updateFlightDetails(Long id, FlightsRequestDTO flightDTO);

    FlightsDTOResponse updateFlightDepartureDate(Long id, FlightsRequestDTO flightDTO);
}
