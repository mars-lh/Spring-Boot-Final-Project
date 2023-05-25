package com.project.java.project.springboot.controller.RestAdmin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.service.Admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminDTOResponse>> loadAllUsers () {
        return ResponseEntity.status(201).body(adminService.findAllUsers());
    }

    @PostMapping ("/reg2")
    public ResponseEntity<AdminDTOResponse> registerAdmin (@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.status(201).body(adminService.registerAdminDTO(adminDTO));
    }

    @PostMapping("/new1")
    public ResponseEntity<AdminDTOResponse> saveUser (@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.ok().body(adminService.registerUserDTODetails(adminDTO));
    }

}
