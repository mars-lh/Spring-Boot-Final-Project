package com.project.java.project.springboot.service.Flight;

import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.repository.FlightRepository;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public FlightsDTORequest flightNumberGenerator(FlightsDTORequest flightsDTORequest) {
        // Generate flight number based on airline code and a unique three-digit number
        String airlineCode = flightsDTORequest.getAirline_code().name();
        String lastFlightNumberDate = null;
        String lastFlightNumber = null;
        String existingFlightCode = null;

        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(flightsDTORequest.getDepartureDate());
        String flightNumberToCheck = airlineCode + formattedDate;
        List<String> flightCodesOfSameDate = new ArrayList<>();
        List<FlightsEntity> flights = flightRepository.findFlightsEntityByAirlineCodeAndDepartureDate(flightsDTORequest.getAirline_code(), flightsDTORequest.getDepartureDate());
        for (FlightsEntity f : flights) {
            String airlineCodeLoop = f.getAirline_code().name();
            String formattedDateLoop = dateFormat.format(f.getDepartureDate());
            String existingFlight = airlineCodeLoop + formattedDateLoop;
             existingFlightCode = f.getFlightNumber();

            if (existingFlight.equals(flightNumberToCheck) && formattedDate.equals(formattedDateLoop)) {
                lastFlightNumberDate = existingFlight;
                flightCodesOfSameDate.add(f.getFlightNumber());

            }
            List<String> orderedList = flightCodesOfSameDate.stream().toList();
            lastFlightNumber = orderedList.get(orderedList.size()-1);
        }

        int nextFlightNumber = 1;
        if (lastFlightNumberDate != null) {
            try {
                int lastNumber = Integer.parseInt(lastFlightNumber.substring(airlineCode.length()));
                nextFlightNumber = lastNumber + 1;
            } catch (NumberFormatException e) {
                System.out.println(e);
            }

        }
        String formattedNumber = String.format("%03d", nextFlightNumber);


        String newFlightNumber = airlineCode + formattedNumber;

        // Generate flight number using the last flight number
//        String flightNumber = generateFlightNumber(airlineCode, lastFlightNumber);

        // Set the generated flight number in the FlightsDTORequest object
        flightsDTORequest.setFlightNumber(newFlightNumber);
        return  flightsDTORequest;

        // Create the flight using the FlightsDTORequest object
        // flightRepository.createFlight(flightsDTORequest);
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
