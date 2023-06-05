package com.project.java.project.springboot.service.booking;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import com.project.java.project.springboot.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserBookingRepository userBookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, UserBookingRepository userBookingRepository, FlightRepository flightRepository, UserRepository userRepository, UserDetailRepository userDetailRepository) {
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
        UserDetailEntity userEntity = userDetailRepository.findByUser(userid);
        userBookingsEntity.setUser(userEntity);
        userBookingsEntity.setBookingStatus(bookingEntity.getBookingStatus());
        userBookingsEntity.setBooking(bookingEntity);
        bookingEntity.setUserBookings(userBookingsEntity.getBooking().getUserBookings());

        userBookingRepository.save(userBookingsEntity);


        BookingResponseDTO updatedBookingResponseDTO = new BookingResponseDTO(bookingEntity);
        return updatedBookingResponseDTO;

    }


    @Override
    public FlightsEntity checkFlight(BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException {
        String flightNumber = bookingRequestDTO.getFlights().getFlightNumber();
        FlightsEntity flight = flightRepository.findFlightsEntityByFlightNumber(flightNumber);

        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }

        return flight;
    }

    @Override
    public Optional<BookingResponseDTO> bookFlightBooking(Long userid, UserRequestDTO userDTO) throws FlightNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(userid);
        BookingResponseDTO bookingDataToReturn = new BookingResponseDTO();
        if (optionalUser.isPresent()) {
            List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<>();
            for (BookingRequestDTO bookingDTO : userDTO.getBookingRequestDTO()) {
                BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
                bookingRequestDTO.setBookingStatusEnum(BookingStatusEnum.BOOKED);
                bookingRequestDTO.setFlights(bookingDTO.getFlights());
                bookingRequestDTOs.add(bookingRequestDTO);
            }
            // Call the saveBooking method for each booking
            for (BookingRequestDTO bookingRequestDTO : bookingRequestDTOs) {
                bookingDataToReturn =  saveBooking(bookingRequestDTO, userid);
            }

        }
        return Optional.of(bookingDataToReturn);
    }

    @Override
    public void cancelBooking(Long id) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        if (adminResponse()) {
            booking.get().setBookingStatus(BookingStatusEnum.CANCELED);
            bookingRepository.save(booking.get());
        } else {
            booking.get().setBookingStatus(BookingStatusEnum.REQUEST_CANCELATION);
            bookingRepository.save(booking.get());
        }
        if (declines()) {
            booking.get().setBookingStatus(BookingStatusEnum.BOOKED);
            bookingRepository.save(booking.get());
        }
    }

    @Override
    public List<BookingResponseDTO> getBookingsforApproval(BookingRequestDTO bookingStatus) {
        List<BookingResponseDTO> bookingList = new ArrayList<>();

        List<BookingEntity> bookingEntityList = bookingRepository.findAll().stream().toList();
        for (BookingEntity booking : bookingEntityList) {
            BookingResponseDTO bookingToSave = mapBookingEntityToResponseDTO(booking);
            if (bookingToSave.getBookingStatus().equals(bookingStatus.getBookingStatusEnum())) {
                bookingList.add(bookingToSave);
            }
        }
//        bookingList.add(new BookingResponseDTO());
        return bookingList;
    }

    private BookingResponseDTO mapBookingEntityToResponseDTO(BookingEntity bookingEntity) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        // Map the properties from BookingEntity to BookingResponseDTO
        bookingResponseDTO.setBookingStatus(bookingEntity.getBookingStatus());
        bookingResponseDTO.setBookingDate(bookingEntity.getBookingDate());
//        bookingResponseDTO.setFlights(bookingEntity.getFlights());
        // Map other properties as needed

        return bookingResponseDTO;
    }

    public boolean adminResponse() {
        return false;
    }

    public boolean declines() {
        return false;
    }


}
