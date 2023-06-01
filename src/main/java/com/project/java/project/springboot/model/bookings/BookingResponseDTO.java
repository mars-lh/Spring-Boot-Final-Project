package com.project.java.project.springboot.model.bookings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

        private Long id;

        private Date bookingDate;

        private BookingStatusEnum bookingStatus;

       private FlightsEntity flights;

        public BookingResponseDTO(BookingEntity bookingEntity) {
                this.bookingDate = bookingEntity.getBookingDate();
                this.bookingStatus = bookingEntity.getBookingStatus();
                this.flights = bookingEntity.getFlights();
        }

      public BookingResponseDTO (UserBookingsEntity userBookingsEntity) {
          BookingEntity booking = new BookingEntity();
          booking.setUserBookings(userBookingsEntity.getUser().getUserBookings());

            this.bookingDate = booking.getBookingDate();
            this.bookingStatus = booking.getBookingStatus();
            this.flights = booking.getFlights();

    }




    private List<UserBookingsResponseDTO> convertToUserBookingsResponseDTO(List<UserBookingsEntity> userBookings) {
        List<UserBookingsResponseDTO> userBookingsDTO = new ArrayList<>();
        for (UserBookingsEntity userBooking : userBookings) {
            UserBookingsResponseDTO userBookingDTO = new UserBookingsResponseDTO(userBooking);
            userBookingsDTO.add(userBookingDTO);
        }
        return userBookingsDTO;
    }


    public void setBooking(BookingRequestDTO booking) {
    }
}

