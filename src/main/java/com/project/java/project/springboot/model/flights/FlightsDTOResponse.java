package com.project.java.project.springboot.model.flights;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightsDTOResponse {

    private Long id;

    private String flightNumber;

    private String originAirport;

    private String originCountry;

    private String destinationAirport;

    private String destinationCountry;

    private AirlinesEnum airline_code;

    private Date departureDate;

    private Date arrivalDate;

    private FlightStatusEnum flightStatus;

    private List<BookingEntity> bookings;


    public FlightsDTOResponse(FlightsEntity flighTosave) {
        this.flightNumber = flighTosave.getFlightNumber();
        this.originAirport = flighTosave.getOriginAirport();
        this.originCountry = flighTosave.getOriginCountry();
        this.destinationAirport = flighTosave.getDestinationAirport();
        this.destinationCountry = flighTosave.getDestinationCountry();
        this.airline_code = flighTosave.getAirline_code();
        this.departureDate = flighTosave.getDepartureDate();
        this.arrivalDate = flighTosave.getArrivalDate();
        this.flightStatus = flighTosave.getFlightStatus();

    }
}
