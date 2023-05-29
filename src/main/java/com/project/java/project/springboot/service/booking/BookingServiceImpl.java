package com.project.java.project.springboot.service.booking;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.repository.BookingRepository;
import com.project.java.project.springboot.repository.FlightRepository;
import com.project.java.project.springboot.repository.UserBookingRepository;
import com.project.java.project.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final UserBookingRepository userBookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl (BookingRepository bookingRepository, UserBookingRepository userBookingRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userBookingRepository = userBookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }
//    @Override
//    public void createBookingWithUserBookings(BookingRequestDTO bookingRequestDTO) {
//        BookingEntity booking = createBooking(bookingRequestDTO);
//        UserBookingsEntity userBooking1 = createUserBooking(booking);
//        UserBookingsEntity userBooking2 = createUserBooking(booking);
//        addBookingToUserBooking(booking, userBooking1);
//        addBookingToUserBooking(booking, userBooking2);
//        saveBooking(bookingRequestDTO);
//    }
    @Override
    public BookingEntity createBooking(BookingRequestDTO bookingDTO) {
        BookingEntity booking = bookingDTO.toEntity();
        return bookingRepository.save(booking);
    }
    @Override
    public UserBookingsEntity createUserBooking(BookingEntity booking) {
        return null;
    }

    @Override
    public void addBookingToUserBooking(BookingEntity booking, UserBookingsEntity userBooking) {

    }

    @Override
    public BookingResponseDTO saveBooking(BookingRequestDTO bookingRequestDTO, Long userid) throws FlightNotFoundException {
        FlightsEntity flight = bookFlight(bookingRequestDTO);
        BookingEntity bookingEntity = bookingRequestDTO.toEntity();
        bookingEntity.setFlights(flight);
        bookingRepository.save(bookingEntity);

        UserBookingsEntity userBookingsEntity = new UserBookingsEntity();
        userBookingsEntity.setBooking(bookingEntity);
        userBookingsEntity.setBookingStatus(bookingEntity.getBookingStatus());

       Optional<UserEntity>  userEntity = userRepository.findById(userid);
        userBookingsEntity.setUser(userEntity.get().getUserDetail());


        userBookingRepository.save(userBookingsEntity);

        return new BookingResponseDTO(bookingEntity);

    }

    @Override
    public FlightsEntity bookFlight (BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException {
        String flightNumber = bookingRequestDTO.getFlights().getFlightNumber();
        FlightsEntity flight = flightRepository.findFlightsEntityByFlightNumber(flightNumber);

        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }

        return flight;
    }


}
