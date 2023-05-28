package com.project.java.project.springboot.service.Admin;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.repository.AdminRepository;
import com.project.java.project.springboot.repository.RoleRepository;
import com.project.java.project.springboot.service.booking.BookingService;
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
    private final BookingService bookingService;

    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository, BookingService bookingService) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.bookingService = bookingService;
    }



    @Override
    public List<AdminDTOResponse> findAllUsers() {
        return adminRepository.findAll()
                .stream()
                .map(AdminDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public AdminEntity registerAdmin(AdminEntity admin) {
      return adminRepository.save(admin);
    }

    @Override
    public AdminDTOResponse registerAdminDTO(AdminDTO admin) {
        AdminEntity adminToSave = admin.toEntity();
        return new AdminDTOResponse(adminRepository.save(adminToSave));
    }

    @Override
    public AdminDTOResponse registerUserDTODetails(AdminDTO adminDTO) {
        AdminEntity adminToSave = adminDTO.toEntityUserDetails();
        adminRepository.save(adminToSave);


        List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<>();
        for (BookingRequestDTO bookingDTO : adminDTO.getUserDetailDTO().getBookingRequestDTO()) {
            BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
            bookingRequestDTO.setBookingStatusEnum(bookingDTO.getBookingStatusEnum());
            // Set any other required fields of the bookingRequestDTO
            bookingRequestDTOs.add(bookingRequestDTO);
        }

        // Call the saveBooking method for each booking
        for (BookingRequestDTO bookingRequestDTO : bookingRequestDTOs) {
            bookingService.saveBooking(bookingRequestDTO);
        }
        return new AdminDTOResponse(adminToSave);
    }

}
