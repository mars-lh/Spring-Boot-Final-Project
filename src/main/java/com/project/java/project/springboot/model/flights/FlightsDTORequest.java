package com.project.java.project.springboot.model.flights;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightsDTORequest {

    private String flightNumber;

    private String originAirport;

    private String originCountry;

    private String destinationAirport;

    private String destinationCountry;

    private AirlinesEnum airline_code;

    private Date departureDate;

    private Date arrivalDate;

    private FlightStatusEnum flightStatus;


    public FlightsEntity toEntity () {
        FlightsEntity flights = new FlightsEntity();
        flights.setFlightNumber(this.flightNumber);
        flights.setAirline_code(this.airline_code);
        flights.setOriginCountry(this.originCountry);
        flights.setOriginAirport(this.originAirport);
        flights.setDestinationCountry(this.destinationCountry);
        flights.setDestinationAirport(this.destinationAirport);
        flights.setDepartureDate(this.departureDate);
        flights.setArrivalDate(this.arrivalDate);
        flights.setFlightStatus(this.flightStatus);
        return flights;
    }


}
