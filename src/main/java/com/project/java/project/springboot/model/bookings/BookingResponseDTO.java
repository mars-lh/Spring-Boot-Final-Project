package com.project.java.project.springboot.model.bookings;

import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
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

        private Date bookingDate;

        private BookingStatusEnum bookingStatus;

       private FlightsEntity flights;



        public BookingResponseDTO(BookingEntity bookingEntity) {
                this.bookingDate = bookingEntity.getBookingDate();
                this.bookingStatus = bookingEntity.getBookingStatus();
                this.flights = bookingEntity.getFlights();
        }
}

