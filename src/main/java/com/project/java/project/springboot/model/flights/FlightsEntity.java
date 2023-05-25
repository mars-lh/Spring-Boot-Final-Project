package com.project.java.project.springboot.model.flights;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "flights")
public class FlightsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column (name = "flight_number", unique = true)
    private String flightNumber;

    @Column (name = "origin_airport")
    private String originAirport;

    @Column (name = "origin_country")
    private String originCountry;

    @Column(name = "destination_airport")
    private String destinationAirport;

    @Column(name = "destination_country")
    private String destinationCountry;

    @Enumerated (value = EnumType.STRING)
    @Column(name = "airline_code")
    private AirlinesEnum airline_code;

    @Column(name = "departure_date")
    @Temporal(value = TemporalType.DATE)
    private Date departureDate;

    @Column(name = "arrival_date")
    @Temporal(value = TemporalType.DATE)
    private Date arrivalDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "flight_status")
    private FlightStatusEnum flightStatus;

    @OneToMany(mappedBy = "flights", cascade = CascadeType.ALL)
    private List <BookingEntity> bookings;
}
