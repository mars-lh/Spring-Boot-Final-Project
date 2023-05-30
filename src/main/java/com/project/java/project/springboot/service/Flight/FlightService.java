package com.project.java.project.springboot.service.Flight;

import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FlightService {

    FlightsDTOResponse createFlight (FlightsDTORequest flightsDTORequest);

    FlightsDTOResponse findFlightByID (Long id);

    FlightsEntity flightEntityforDate (Long id);

    List<FlightsDTOResponse> findALlFlights ();

    void deleteFlightById (Long id);

    Optional<FlightsDTOResponse> updateFlightDetails (Long id, FlightsDTORequest flightDTO);

    FlightsDTOResponse updateFlightDepartureDate(Long id, FlightsDTORequest flightDTO);
}
