package com.project.java.project.springboot.model.userBookings;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookingsRequestDTO {

    private Long id;

    private UserDetailDTORequest user;

    private BookingRequestDTO booking;

    private BookingStatusEnum bookingStatus;

    public UserBookingsRequestDTO(Long id) {
    }


    public UserBookingsEntity toEntity() {
        UserBookingsEntity userBookings = new UserBookingsEntity();
        userBookings.setId(this.id);
        if (user != null) {
            userBookings.setUser(user.toEntity());
        }
        if (booking != null) {
            BookingEntity bookingEntity = booking.toEntity();
            bookingEntity.setBookingStatus(BookingStatusEnum.ACTIVE);
            userBookings.setBooking(bookingEntity);
        }

        userBookings.setBookingStatus(bookingStatus);

        return userBookings;
    }

}

