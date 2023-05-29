package com.project.java.project.springboot.service.Admin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService {


    List<AdminDTOResponse> findAllUsers();

    UserDetailDTOResponse findUserByEmail(String email);

    AdminEntity registerAdmin (AdminEntity admin);

    AdminDTOResponse registerAdminDTO (AdminDTO admin);

    AdminDTOResponse registerUserDTODetails(AdminDTO admin) throws FlightNotFoundException;


}
