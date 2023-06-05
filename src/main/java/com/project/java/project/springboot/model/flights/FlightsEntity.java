package com.project.java.project.springboot.model.flights;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.repository.FlightRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

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


}

