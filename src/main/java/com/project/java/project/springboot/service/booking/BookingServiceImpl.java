package com.project.java.project.springboot.service.booking;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.repository.BookingRepository;
import com.project.java.project.springboot.repository.FlightRepository;
import com.project.java.project.springboot.repository.UserBookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final UserBookingRepository userBookingRepository;
    private final FlightRepository flightRepository;

    public BookingServiceImpl (BookingRepository bookingRepository, UserBookingRepository userBookingRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.userBookingRepository = userBookingRepository;
        this.flightRepository = flightRepository;
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
    public BookingResponseDTO saveBooking(BookingRequestDTO bookingRequestDTO) {
        BookingEntity bookingEntity = bookingRequestDTO.toEntity();
        bookingRepository.save(bookingEntity);

        UserBookingsEntity userBookingsEntity = new UserBookingsEntity();
        userBookingsEntity.setBooking(bookingEntity);
        userBookingsEntity.setBookingStatus(bookingEntity.getBookingStatus());

        userBookingRepository.save(userBookingsEntity);

        return new BookingResponseDTO(bookingEntity);

    }


}
