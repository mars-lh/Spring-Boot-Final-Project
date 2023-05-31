package com.project.java.project.springboot.service.userBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import com.project.java.project.springboot.repository.BookingRepository;
import com.project.java.project.springboot.repository.UserBookingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserbookingServiceImpl implements UserbookingService{

    private final UserBookingRepository userBookingRepository;
    private final BookingRepository bookingRepository;

    public UserbookingServiceImpl(UserBookingRepository userBookingRepository,BookingRepository bookingRepository) {
        this.userBookingRepository = userBookingRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    public UserBookingsResponseDTO createUserBooking(UserBookingsRequestDTO userBookingsRequestDTO) {
        UserBookingsEntity userBookingsEntity = userBookingsRequestDTO.toEntity();
        return new UserBookingsResponseDTO(userBookingRepository.save(userBookingsEntity));
    }

    @Override
    public void setUserToUserBooking(BookingRequestDTO BookingRequestDTO) {
        userBookingRepository.findById(1L);
    }
}
