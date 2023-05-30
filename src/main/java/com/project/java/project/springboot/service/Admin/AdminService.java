package com.project.java.project.springboot.service.Admin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {


    List<AdminDTOResponse> findAllUsers();

    UserDetailDTOResponse findUserByEmail(String email);

//    List<BookingResponseDTO> findUserBookings (Long id);

    AdminDTOResponse registerAdminDTO (AdminDTO admin);

    AdminDTOResponse registerAdminDTODetails(AdminDTO admin) throws FlightNotFoundException;

    UserDTOResponse registerUserDTO(UserDTORequest userDTO);
}
