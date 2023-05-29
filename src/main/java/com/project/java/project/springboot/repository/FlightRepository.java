package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.flights.FlightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository <FlightsEntity, Long> {

    void deleteById(Long id);

    FlightsEntity findFlightsEntityByFlightNumber(String searchString);
}
