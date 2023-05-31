package com.project.java.project.springboot.model.userBookings;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import lombok.Data;

import java.awt.print.Book;
import java.util.List;

@Data
public class UserBookingsResponseDTO {

    private BookingResponseDTO bookingResponseDTO;


//    public UserBookingsResponseDTO(UserBookingsEntity userBookingsEntity) {
//
//        userBookingsEntity.setBooking(this.booking.toEntity());
//        userBookingsEntity.setBookingStatus(this.bookingStatus);
//
//    }

    public UserBookingsResponseDTO(UserBookingsEntity userBookingsEntity) {

        this.bookingResponseDTO = new BookingResponseDTO(userBookingsEntity);

    }
}
