package com.project.java.project.springboot.controller.RestBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PostMapping("/newBooking")
//    ResponseEntity<BookingResponseDTO> saveBooking (@RequestBody BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException {
//        return ResponseEntity.ok(bookingService.saveBooking(bookingRequestDTO));
//    }
}
