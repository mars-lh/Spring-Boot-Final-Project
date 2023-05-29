package com.project.java.project.springboot.controller.RestAdmin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.Admin.AdminService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AdminController {

    private final AdminService adminService;

    private final UserDetailsService userDetailsService;

    public AdminController(AdminService adminService, UserDetailsService userDetailsService) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
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


    @PostMapping ("/reg2")
    public ResponseEntity<AdminDTOResponse> registerAdmin (@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.status(201).body(adminService.registerAdminDTO(adminDTO));
    }

    @PostMapping("/new1")
    public ResponseEntity<AdminDTOResponse> saveUser (@RequestBody AdminDTO adminDTO) throws FlightNotFoundException {
        return ResponseEntity.ok().body(adminService.registerUserDTODetails(adminDTO));
    }

}
