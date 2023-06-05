package com.project.java.project.springboot.service.Admin;

import com.project.java.project.springboot.model.admin.AdminRequestDTO;
import com.project.java.project.springboot.model.admin.AdminResponseDTO;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {


    List<AdminResponseDTO> findAllUsers();

    List<UserDetailDTOResponse> findAllUserBookings();

    UserDetailDTOResponse findUserByEmail(String email);

    AdminResponseDTO registerAdminDTO (AdminRequestDTO adminRequestDTO);

    AdminResponseDTO registerAdminDTODetails(AdminRequestDTO admin) throws FlightNotFoundException;

    UserResponseDTO registerUserDTO(UserRequestDTO userDTO);

    UserDetailDTOResponse updateUser (UserDetailDTORequest userDetail, Long id);



}
