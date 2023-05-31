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
public class FlightsDTORequest {

    private Long id;

    private String flightNumber;

    private String originAirport;

    private String originCountry;

    private String destinationAirport;

    private String destinationCountry;

    private AirlinesEnum airline_code;

    private Date flightDate;

    private Integer economySeats;

    private Integer businessSeats;

    private Integer firstClassSeats;

    private Date departureDate;

    private Date arrivalDate;

    private FlightStatusEnum flightStatus;

    private List <BookingEntity> bookings;


    public FlightsEntity toEntity () {
        FlightsEntity flights = new FlightsEntity();
        flights.setId(this.id);
        flights.setFlightNumber(this.flightNumber);
        flights.setAirline_code(this.airline_code);
        flights.setOriginCountry(this.originCountry);
        flights.setOriginAirport(this.originAirport);
        flights.setDestinationCountry(this.destinationCountry);
        flights.setDestinationAirport(this.destinationAirport);
        flights.setDepartureDate(this.departureDate);
        flights.setArrivalDate(this.arrivalDate);
        flights.setFlightDate(this.departureDate);
        flights.setFlightStatus(this.flightStatus);
        if(economySeats != null) {
            flights.setPremiumEconomySeats(this.economySeats);
        } else if (businessSeats != null) {
            this.economySeats = 0;
            flights.setBusinessSeats(this.businessSeats);
        } else if (firstClassSeats != null ){
            this.economySeats = 0;
            this.businessSeats = 0;
            flights.setFirstClassSeats(this.firstClassSeats);
        }
        if (bookings != null) {
            for (BookingEntity booking : bookings) {
                booking.setFlights(flights);
            }
        }

        return flights;
    }


}
