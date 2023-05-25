package com.project.java.project.springboot.service.Flight;

import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public FlightsDTOResponse createFlight(FlightsDTORequest flightsDTORequest) {
        FlightsEntity flighTosave = flightsDTORequest.toEntity();
        flightRepository.save(flighTosave);
        return new FlightsDTOResponse(flighTosave);
    }

    @Override
    public Optional<FlightsDTOResponse> findFlightByID(Long id) {
        return flightRepository.findById(id).map(FlightsDTOResponse::new);

    }

    @Override
    public List<FlightsDTOResponse> findALlFlights() {
        return flightRepository.findAll().stream().map(FlightsDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }


}
