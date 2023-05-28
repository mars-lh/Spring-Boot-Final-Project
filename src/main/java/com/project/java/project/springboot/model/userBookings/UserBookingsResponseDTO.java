package com.project.java.project.springboot.model.userBookings;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import lombok.Data;

@Data
public class UserBookingsResponseDTO {

    private Long id;

    private BookingRequestDTO booking;

    private BookingStatusEnum bookingStatus;


    public UserBookingsResponseDTO(UserBookingsEntity userBookingsEntity) {

        userBookingsEntity.setBooking(this.booking.toEntity());
        userBookingsEntity.setBookingStatus(this.bookingStatus);

    }
}
