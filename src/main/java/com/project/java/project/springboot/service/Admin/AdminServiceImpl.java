package com.project.java.project.springboot.service.Admin;

import com.project.java.project.springboot.model.admin.AdminRequestDTO;
import com.project.java.project.springboot.model.admin.AdminResponseDTO;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import com.project.java.project.springboot.repository.*;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    public List<AdminResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(AdminResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<UserDetailDTOResponse> findAllUserBookings() {
        return userDetailRepository.findAll()
                .stream()
                .map(UserDetailDTOResponse::new).collect(Collectors.toList());
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
    public AdminResponseDTO registerAdminDTO(AdminRequestDTO adminRequestDTO) {
        AdminEntity adminToSave = adminRequestDTO.toEntity();
        return new AdminResponseDTO(adminRepository.save(adminToSave));
    }

    @Override
    public AdminResponseDTO registerAdminDTODetails(AdminRequestDTO adminRequestDTO) throws FlightNotFoundException {
        AdminEntity adminToSave = adminRequestDTO.toEntityUserDetails();
        adminRepository.save(adminToSave);
        return new AdminResponseDTO(adminToSave);
    }

    @Override
    public UserResponseDTO registerUserDTO(UserRequestDTO userDTO) {
        UserEntity userTosave = userDTO.toEntityUserDetails();
        userRepository.save(userTosave);
        return new UserResponseDTO(userDTO);
    }

    @Override
    public UserDetailDTOResponse updateUser (UserDetailDTORequest userDetail, Long id) {
       UserDetailEntity userToUpdate = userDetailRepository.findByUser(id);
       userToUpdate.setFirstName(userDetail.getFirstName());
       userToUpdate.setLastName(userDetail.getLastName());
       userToUpdate.setEmail(userDetail.getEmail());
       userToUpdate.setPhoneNumber(userDetail.getPhoneNumber());
       userDetailRepository.save(userToUpdate);
       UserDetailDTOResponse responseDTO = new UserDetailDTOResponse();
       responseDTO = mapToDTO(userToUpdate);
       return responseDTO;
    }

}
