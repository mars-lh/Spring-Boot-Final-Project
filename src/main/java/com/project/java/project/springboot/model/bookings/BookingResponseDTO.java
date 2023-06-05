package com.project.java.project.springboot.model.bookings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {


    private Date bookingDate;

    private BookingStatusEnum bookingStatus;

    private FlightsDTOResponse flights;

    public BookingResponseDTO(BookingEntity bookingEntity) {
        this.bookingDate = bookingEntity.getBookingDate();
        this.bookingStatus = bookingEntity.getBookingStatus();
        this.flights = new FlightsDTOResponse(bookingEntity.getFlights());
    }
}


