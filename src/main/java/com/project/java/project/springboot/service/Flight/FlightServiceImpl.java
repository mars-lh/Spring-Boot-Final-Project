package com.project.java.project.springboot.service.Flight;

import com.project.java.project.springboot.model.enums.FlightStatusEnum;
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
    public FlightsDTOResponse findFlightByID(Long id) {
        Optional<FlightsEntity> optionalFlights = flightRepository.findById(id);
        FlightsEntity findFlightEntity = optionalFlights.get();
        FlightsDTOResponse DTOtoReturn = mapToDTO(findFlightEntity);
        return DTOtoReturn;

    }

    @Override
    public FlightsEntity flightEntityforDate(Long id) {
        return flightRepository.findFlightsEntityById(id);
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

    @Override
    public FlightsDTOResponse updateFlightDepartureDate(Long id, FlightsDTORequest flightDTO) {
        Optional<FlightsEntity> optionalFlights = flightRepository.findById(id);

        FlightsEntity existingFlight = optionalFlights.get();
        existingFlight.setDepartureDate(flightDTO.getDepartureDate());
        flightRepository.save(existingFlight);
        FlightsDTOResponse updatedFlightDTO = new FlightsDTOResponse(existingFlight);
        return updatedFlightDTO;
    }

    public FlightsDTOResponse mapToDTO (FlightsEntity flights) {
        FlightsDTOResponse flightsDTOResponse = new FlightsDTOResponse();
        flightsDTOResponse.setId(flights.getId());
        flightsDTOResponse.setFlightNumber(flights.getFlightNumber());
        flightsDTOResponse.setFlightStatus(flights.getFlightStatus());
        flightsDTOResponse.setDestinationAirport(flights.getDestinationAirport());
        flightsDTOResponse.setDestinationCountry(flights.getDestinationCountry());
        flightsDTOResponse.setOriginAirport(flights.getOriginAirport());
        flightsDTOResponse.setOriginCountry(flights.getOriginCountry());
        flightsDTOResponse.setAirline_code(flights.getAirline_code());
        flightsDTOResponse.setBookings(flights.getBookings());
        return flightsDTOResponse;
    }


}
