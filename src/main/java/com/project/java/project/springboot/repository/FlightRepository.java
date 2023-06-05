package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.enums.AirlinesEnum;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository <FlightsEntity, Long> {

    void deleteById(Long id);

    FlightsEntity findFlightsEntityByFlightNumber(String searchString);

    FlightsEntity findFlightsEntityById (Long id);

    @Query("SELECT f FROM FlightsEntity f WHERE f.airline_code = :airlineCode AND f.departureDate = :departureDate")
    List<FlightsEntity> findFlightsEntityByAirlineCodeAndDepartureDate(@Param("airlineCode") AirlinesEnum airlineCode,
                                                                      @Param("departureDate") Date departureDate);

    List<FlightsEntity> findAllByDestinationCountryAndOriginCountryAndDepartureDate (String destination, String origin, Date departureDate);
    @Query("SELECT f FROM FlightsEntity f WHERE f.destinationCountry = :destinationCountry AND f.originCountry = :originCountry AND f.departureDate = :departureDate AND f.airline_code = :airlineCode")
    List<FlightsEntity> findAllByDestinationCountryAndOriginCountryAndDepartureDateAndAirlineCode(
            @Param("destinationCountry") String destinationCountry,
            @Param("originCountry") String originCountry,
            @Param("departureDate") Date departureDate,
            @Param("airlineCode") AirlinesEnum airlineCode
    );

}
