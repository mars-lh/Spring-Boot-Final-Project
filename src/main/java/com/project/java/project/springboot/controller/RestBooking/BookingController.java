package com.project.java.project.springboot.controller.RestBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PutMapping("/{userid}")
    public ResponseEntity<Optional<BookingResponseDTO>> bookFlight (@PathVariable(value = "userid")Long id, @RequestBody UserRequestDTO userRequestDTO) throws FlightNotFoundException {
        Optional<BookingResponseDTO> update = bookingService.bookFlightBooking(id, userRequestDTO);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity cancelBooking (@PathVariable(value = "bookingId") Long id) {
        bookingService.cancelBooking(id);
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(200);
    }

}
