package com.project.java.project.springboot.service.booking;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {


     void addBookingToUserBooking(BookingEntity booking, UserBookingsEntity userBooking);

     BookingResponseDTO saveBooking(BookingRequestDTO bookingRequestDTO, Long userid) throws FlightNotFoundException;

     FlightsEntity checkFlight(BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException;

     Optional<BookingResponseDTO> bookFlightBooking (Long id, UserRequestDTO userDTO) throws FlightNotFoundException;

     void cancelBooking (Long id);

     List<BookingResponseDTO> getBookingsforApproval (BookingRequestDTO bookingStatus);

}
