package com.project.java.project.springboot.controller.RestBooking;

import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/newBooking/{userid}")
    ResponseEntity<BookingResponseDTO> saveBooking (@PathVariable(value = "userid")Long id, @RequestBody BookingRequestDTO bookingRequestDTO) throws FlightNotFoundException {
        return ResponseEntity.ok(bookingService.saveBooking(bookingRequestDTO, id));
    }


    @PutMapping("/bookflight1/{userid}")
    public ResponseEntity<Optional<UserDTOResponse>> bookFlight (@PathVariable(value = "userid")Long id, @RequestBody UserDTORequest userDTORequest) throws FlightNotFoundException {
        Optional<UserDTOResponse> update = bookingService.bookFlightBooking(id, userDTORequest);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity cancelBooking (@PathVariable(value = "bookingId") Long id) {
        bookingService.cancelBooking(id);
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(200);
    }

}
