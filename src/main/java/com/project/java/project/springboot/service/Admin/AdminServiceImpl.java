package com.project.java.project.springboot.service.Admin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import com.project.java.project.springboot.repository.*;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
public class AdminServiceImpl implements AdminService {

    public final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository, BookingRepository bookingRepository, UserRepository userRepository, UserDetailRepository userDetailRepository) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
    }


    @Override
    public List<AdminDTOResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(AdminDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public UserDetailDTOResponse findUserByEmail(String email) {
        UserDetailDTOResponse userDetailResponseDTO = mapToDTO(userDetailRepository.findByEmail(email));
        return userDetailResponseDTO;
    }

    private UserDetailDTOResponse mapToDTO(UserDetailEntity userDetailEntity) {
        UserDetailDTOResponse userDetailDTOResponse = new UserDetailDTOResponse();
        userDetailDTOResponse.setFirstName(userDetailEntity.getFirstName());
        userDetailDTOResponse.setLastName(userDetailEntity.getFirstName());
        userDetailDTOResponse.setMiddleName(userDetailEntity.getMiddleName());
        userDetailDTOResponse.setEmail(userDetailEntity.getEmail());
        userDetailDTOResponse.setUserRole(userDetailEntity.getUserRole());
        userDetailDTOResponse.setPhoneNumber(userDetailEntity.getPhoneNumber());
//        userDetailDTOResponse.setUserBookings(userDetailEntity.getUserBookings());

        return userDetailDTOResponse;
    }

    @Override
    public AdminDTOResponse registerAdminDTO(AdminDTO admin) {
        AdminEntity adminToSave = admin.toEntity();
        return new AdminDTOResponse(adminRepository.save(adminToSave));
    }

    @Override
    public AdminDTOResponse registerAdminDTODetails(AdminDTO adminDTO) throws FlightNotFoundException {
        AdminEntity adminToSave = adminDTO.toEntityUserDetails();
        adminRepository.save(adminToSave);
        return new AdminDTOResponse(adminToSave);
    }

    @Override
    public UserDTOResponse registerUserDTO(UserDTORequest userDTO) {
        UserEntity userTosave = userDTO.toEntityUserDetails();
        userRepository.save(userTosave);
        return new UserDTOResponse(userDTO);
    }

}
