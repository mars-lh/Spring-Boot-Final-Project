package com.project.java.project.springboot.service.Flight;

import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsRequestDTO;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.repository.FlightRepository;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public FlightsDTOResponse createFlight(FlightsRequestDTO flightsRequestDTO) {
        String airlineCode = flightsRequestDTO.getAirline_code().toString().toUpperCase(); // Convert the input value to uppercase

        boolean validAirline = Arrays.stream(AirlinesEnum.values())
                .map(Enum::name)
                .anyMatch(airline -> airline.equals(airlineCode));
        if (!validAirline) {
            throw new NoSuchElementException("Invalid AirlineCode");
        }
        FlightsEntity flighTosave = flightsRequestDTO.toEntity();
        flightRepository.save(flighTosave);
        return new FlightsDTOResponse(flighTosave);
    }

    @Override
    public FlightsRequestDTO flightNumberGenerator(FlightsRequestDTO flightsRequestDTO) {
        // Generate flight number based on airline code and a unique three-digit number
        String airlineCode = flightsRequestDTO.getAirline_code().name();
        String lastFlightNumberDate = null;
        String lastFlightNumber = null;
        String existingFlightCode = null;

        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(flightsRequestDTO.getDepartureDate());
        String flightNumberToCheck = airlineCode + formattedDate;
        List<String> flightCodesOfSameDate = new ArrayList<>();
        List<FlightsEntity> flights = flightRepository.findFlightsEntityByAirlineCodeAndDepartureDate(flightsRequestDTO.getAirline_code(), flightsRequestDTO.getDepartureDate());
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
            lastFlightNumber = orderedList.get(orderedList.size() - 1);
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
        flightsRequestDTO.setFlightNumber(newFlightNumber);
        return flightsRequestDTO;
    }


    @Override
    public FlightsDTOResponse findFlightByID(Long id) {
        FlightsEntity findFlightEntity = flightRepository.findFlightsEntityById(id);
        if (findFlightEntity == null) {
            throw new NullPointerException("Flight not found for ID: " + id);
        }
        FlightsDTOResponse DTOtoReturn = new FlightsDTOResponse(findFlightEntity);
        return DTOtoReturn;
    }

    @Override
    public FlightsEntity findFlightEntity(Long id) {
        return flightRepository.findFlightsEntityById(id);
    }

    @Override
    public List<FlightsDTOResponse> findALlFlights() {
        return flightRepository.findAll().stream().map(FlightsDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public Optional<List<FlightsDTOResponse>> searchFlightsByUser(FlightsRequestDTO flightsRequestDTO) {
//        String flightNumberString = flightsRequestDTO.getFlightNumber();
//        AirlinesEnum airlinesEnum = AirlinesEnum.valueOf(flightNumberString);

        if (flightsRequestDTO.getAirline_code() != null) {
           List<FlightsDTOResponse> DTOResultWithFlightCode = new ArrayList<>();
           for (FlightsEntity flightTopass : flightRepository.findAllByDestinationCountryAndOriginCountryAndDepartureDateAndAirlineCode(
                   flightsRequestDTO.getDestinationCountry(),
                   flightsRequestDTO.getOriginCountry(),
                   flightsRequestDTO.getDepartureDate(),
                   flightsRequestDTO.getAirline_code())) {
               FlightsDTOResponse newDTO = new FlightsDTOResponse(flightTopass);
               DTOResultWithFlightCode.add(newDTO);
           }  return Optional.of(DTOResultWithFlightCode);
       }
        List<FlightsDTOResponse> DTOResult = new ArrayList<>();
        for (FlightsEntity flightTopass : flightRepository.findAllByDestinationCountryAndOriginCountryAndDepartureDate(flightsRequestDTO.getDestinationCountry(), flightsRequestDTO.getOriginCountry(), flightsRequestDTO.getDepartureDate())) {
            FlightsDTOResponse newDTO = new FlightsDTOResponse(flightTopass);
            DTOResult.add(newDTO);
        }
        return Optional.of(DTOResult);
    }

    @Override
    public void deleteFlightById(Long id) {
        FlightsEntity existingFlight = flightRepository.findFlightsEntityById(id);
        if(existingFlight == null) {
            throw new NoSuchElementException("Flight does not exist");
        } else if (existingFlight.getFlightStatus().equals(FlightStatusEnum.BOOKED)) {
            throw new IllegalArgumentException("Flight could not be deleted because is already booked");
        }
        flightRepository.deleteById(id);
    }


    @Override
    public Optional<FlightsDTOResponse> updateFlightDetails(Long id, FlightsRequestDTO flightDTO) {
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
    public FlightsDTOResponse updateFlightDepartureDate(Long id, FlightsRequestDTO flightDTO) {
        Optional<FlightsEntity> optionalFlights = flightRepository.findById(id);

        FlightsEntity existingFlight = optionalFlights.get();
        if (existingFlight.getFlightStatus().equals(FlightStatusEnum.BOOKED)) {
            if (!flightDTO.getDepartureDate().equals(existingFlight.getDepartureDate())) {
                if (flightDTO.getDepartureDate().before(existingFlight.getArrivalDate())) {
                    existingFlight.setDepartureDate(flightDTO.getDepartureDate());
                    flightRepository.save(existingFlight);
                } else {
                    throw new IllegalArgumentException("Departure date cannot be after arrival date.");
                }
            } else {
                throw new IllegalArgumentException("Cannot update other fields when the flight is BOOKED. Only the departure date can be updated.");
            }
        }

        FlightsDTOResponse updatedFlightDTO = new FlightsDTOResponse(existingFlight);
        return updatedFlightDTO;
    }



}