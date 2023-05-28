package com.project.java.project.springboot.controller.RestBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.service.booking.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/newBooking")
    ResponseEntity<BookingResponseDTO> saveBooking (BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingService.saveBooking(bookingRequestDTO));
    }
}
