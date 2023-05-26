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

    @Override
    public Optional<FlightsDTOResponse> updateFlightDetails(Long id, FlightsDTORequest flightDTO) {
        Optional<FlightsEntity> optionalFlights = flightRepository.findById(id);
        if (optionalFlights.isPresent()) {
            FlightsEntity existingFlight = optionalFlights.get();
            existingFlight.setFlightNumber(flightDTO.getFlightNumber());
            existingFlight.setFlightStatus(flightDTO.getFlightStatus());
            existingFlight.setDestinationAirport(flightDTO.getDestinationAirport());
            existingFlight.setDestinationCountry(flightDTO.getDestinationCountry());
            existingFlight.setOriginAirport(flightDTO.getOriginAirport());
            existingFlight.setOriginCountry(flightDTO.getOriginCountry());
            existingFlight.setAirline_code(flightDTO.getAirline_code());
            existingFlight.setBookings(flightDTO.getBookings());
            flightRepository.save(existingFlight);
            FlightsDTOResponse updatedFlightDTO = new FlightsDTOResponse(existingFlight);
            return Optional.of(updatedFlightDTO);

        } else {
            return Optional.of(createFlight(flightDTO));
        }

    }


}
