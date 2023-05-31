package com.project.java.project.springboot.service.userBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserbookingService{

    UserBookingsResponseDTO createUserBooking (UserBookingsRequestDTO userBookingsRequestDTO);

    void setUserToUserBooking (BookingRequestDTO BookingRequestDTO);

}
