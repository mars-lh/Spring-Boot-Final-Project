package com.project.java.project.springboot.controller.RestUserBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.userBooking.UserbookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBookingController {

    private final UserbookingService userbookingService;


    public UserBookingController(UserbookingService userbookingService) {
        this.userbookingService = userbookingService;
    }

    @PostMapping("/newUserBooking")
    ResponseEntity<UserBookingsResponseDTO> saveBooking (UserBookingsRequestDTO userBookingsRequestDTO) {
        return ResponseEntity.ok(userbookingService.createUserBooking(userBookingsRequestDTO));
    }
}
