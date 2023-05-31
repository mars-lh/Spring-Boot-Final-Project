package com.project.java.project.springboot.service.booking;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import com.project.java.project.springboot.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final UserBookingRepository userBookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public BookingServiceImpl (BookingRepository bookingRepository, UserBookingRepository userBookingRepository, FlightRepository flightRepository, UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.bookingRepository = bookingRepository;
        this.userBookingRepository = userBookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public void addBookingToUserBooking(BookingEntity booking, UserBookingsEntity userBooking) {

    }

    @Override
    public BookingResponseDTO saveBooking(BookingRequestDTO bookingRequestDTO, Long userid) throws FlightNotFoundException {
        FlightsEntity flight = checkFlight(bookingRequestDTO);
        BookingEntity bookingEntity = bookingRequestDTO.toEntity();
        bookingEntity.setFlights(flight);
        bookingRepository.save(bookingEntity);

        UserBookingsEntity userBookingsEntity = new UserBookingsEntity();
        UserDetailEntity  userEntity = userDetailRepository.findByUser(userid);
        userBookingsEntity.setUser(userEntity);
        userBookingsEntity.setBookingStatus(bookingEntity.getBookingStatus());
        userBookingsEntity.setBooking(bookingEntity);
        bookingEntity.setUserBookings(userBookingsEntity.getBooking().getUserBookings());

        userBookingRepository.save(userBookingsEntity);


        return new BookingResponseDTO(bookingEntity);

    }

    @Override
    public FlightsEntity checkFlight (BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException {
        String flightNumber = bookingRequestDTO.getFlights().getFlightNumber();
        FlightsEntity flight = flightRepository.findFlightsEntityByFlightNumber(flightNumber);

        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }

        return flight;
    }


}
