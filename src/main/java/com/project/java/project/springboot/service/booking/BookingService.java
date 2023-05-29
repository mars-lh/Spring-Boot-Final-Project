package com.project.java.project.springboot.service.booking;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

//     void createBookingWithUserBookings(BookingRequestDTO bookingRequestDTO);

     BookingEntity createBooking(BookingRequestDTO bookinDTO);

     UserBookingsEntity createUserBooking(BookingEntity booking);


     void addBookingToUserBooking(BookingEntity booking, UserBookingsEntity userBooking);

     BookingResponseDTO saveBooking(BookingRequestDTO bookingRequestDTO, Long userid) throws FlightNotFoundException;

     FlightsEntity bookFlight (BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException;

}
