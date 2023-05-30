package com.project.java.project.springboot.controller.RestAdmin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.custom.CustomResponse;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.Admin.AdminService;
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

    public AdminController(AdminService adminService, UserDetailsService userDetailsService, UserService userService) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all/admins")
    public ResponseEntity<List<AdminDTOResponse>> loadAllAdmins () {
        return ResponseEntity.status(201).body(adminService.findAllUsers());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all/users")
    public ResponseEntity<List<AdminDTOResponse>> loadAllUsers () {
        return ResponseEntity.status(201).body(adminService.findAllUsers());
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
    public ResponseEntity<UserDTOResponse> createUser (@RequestBody UserDTORequest userDTO) throws FlightNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUserDTODetails(userDTO));

    }

    @PostMapping("/new1")
    public ResponseEntity<AdminDTOResponse> createAdmin (@RequestBody AdminDTO adminDTO) throws FlightNotFoundException {
        return ResponseEntity.ok().body(adminService.registerAdminDTODetails(adminDTO));
    }


}
