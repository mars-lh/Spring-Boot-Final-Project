package com.project.java.project.springboot.controller.RestAdmin;

import com.project.java.project.springboot.model.admin.AdminRequestDTO;
import com.project.java.project.springboot.model.admin.AdminResponseDTO;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.Admin.AdminService;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import com.project.java.project.springboot.service.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    private final AdminService adminService;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    private final BookingService bookingService;

    public AdminController(AdminService adminService, UserDetailsService userDetailsService, UserService userService, BookingService bookingService) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all/admins")
    public ResponseEntity<List<AdminResponseDTO>> loadAllAdmins () {
        return ResponseEntity.status(201).body(adminService.findAllUsers());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all/users")
    public ResponseEntity<List<AdminResponseDTO>> loadAllUsers () {
        return ResponseEntity.status(201).body(adminService.findAllUsers());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all/users/bookings")
    public ResponseEntity<List<UserDetailDTOResponse>> loadUserBookings () {
        return ResponseEntity.status(201).body(adminService.findAllUserBookings());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDetailDTOResponse> updateUser (@RequestBody UserDetailDTORequest dtoRequest, @PathVariable(value = "id") Long id) {
        return ResponseEntity.status(201).body(adminService.updateUser(dtoRequest, id));
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/email")
    public ResponseEntity<UserDetailDTOResponse> findByEmail (@RequestBody Map<String, String> requestBody) {
         String email = requestBody.get("email");
         UserDetailDTOResponse result = adminService.findUserByEmail(email);
         return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping ("/create")
    public ResponseEntity<UserResponseDTO> createUser (@RequestBody UserRequestDTO userDTO) throws FlightNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUserDTODetails(userDTO));

    }

    @PostMapping("/new1")
    public ResponseEntity<AdminResponseDTO> createAdmin (@RequestBody AdminRequestDTO adminDTORequest) throws FlightNotFoundException {
        return ResponseEntity.ok().body(adminService.registerAdminDTODetails(adminDTORequest));
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/approve")
    public ResponseEntity<List<BookingResponseDTO>> checkBookingsForApproval (@RequestBody BookingRequestDTO bookingStatus) {
        return ResponseEntity.ok(bookingService.getBookingsforApproval(bookingStatus));
    }


}
