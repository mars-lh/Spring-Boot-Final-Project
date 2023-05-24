package com.project.java.project.springboot.service.adminService;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.user.UserDTORequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {


    List<AdminDTOResponse> findAllUsers();

    AdminEntity registerAdmin (AdminEntity admin);

    AdminDTOResponse registerAdminDTO (AdminDTO admin);

    AdminDTOResponse registerUserDTODetails(AdminDTO admin);
}
