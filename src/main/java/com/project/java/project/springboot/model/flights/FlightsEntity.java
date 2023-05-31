package com.project.java.project.springboot.model.flights;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "flights")
public class FlightsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "flight_number", unique = true, nullable = false)
    private String flightNumber;

    @Column(name = "origin_airport", nullable = false)
    private String originAirport;

    @Column(name = "origin_country", nullable = false)
    private String originCountry;

    @Column(name = "destination_airport", nullable = false)
    private String destinationAirport;

    @Column(name = "destination_country", nullable = false)
    private String destinationCountry;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "airline_code")
    private AirlinesEnum airline_code;

    @Column(name = "flight_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date flightDate;

    @Column(name = "departure_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date departureDate;

    @Column(name = "arrival_date")
    @Temporal(value = TemporalType.DATE)
    private Date arrivalDate;

    @Column(name = "premium_economy_seats")
    private Integer premiumEconomySeats;

    @Column(name = "business_seats")
    private Integer businessSeats;

    @Column(name = "first_seats")
    private Integer firstClassSeats;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "flight_status")
    private FlightStatusEnum flightStatus;

    @OneToMany(mappedBy = "flights", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;

    @PrePersist
    public void generateFlightNumber() {
        // Generate flight number based on airline code and a unique three-digit number
        String airlineCode = this.airline_code.name();

        // Retrieve the last three-digit number used for flights with the same airline code and departure date
        String lastFlightNumber = getLastFlightNumber(airlineCode, this.departureDate);

        // Increment the last flight number or start from 001 if no previous flight number exists
        int nextFlightNumber = 1;
        if (lastFlightNumber != null) {
            int lastNumber = Integer.parseInt(lastFlightNumber.substring(airlineCode.length()));
            nextFlightNumber = lastNumber + 1;
        }
        String formattedNumber = String.format("%03d", nextFlightNumber);

        // Set the flight number as the airline code followed by the unique three-digit number
        this.flightNumber = airlineCode + formattedNumber;
    }

    private String getLastFlightNumber(String airlineCode, Date departureDate) {
        FlightsEntity flights = new FlightsEntity();

        // Implement the logic to retrieve the last flight number used for flights with the same airline code and departure date
        // This can be done by querying the database or any other suitable method
        // Return null if no previous flight number exists

        // Example implementation using a simple in-memory map
        Map<String, String> flightNumbersMap = new HashMap<>();
        String key = airlineCode + departureDate.toString();
        return flightNumbersMap.getOrDefault(key, null);
    }
}

