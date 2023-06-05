package com.project.java.project.springboot.service.userService;

import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.user.UserRequestDTO;
import com.project.java.project.springboot.model.user.UserResponseDTO;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.repository.*;
import com.project.java.project.springboot.service.booking.BookingService;
import com.project.java.project.springboot.service.booking.FlightNotFoundException;
import com.project.java.project.springboot.service.userBooking.UserbookingService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final BookingService bookingService;
    private final UserDetailRepository userDetailRepository;

    public UserServiceImpl(UserRepository userRepository, AdminRepository adminRepository, BookingService bookingService, UserDetailRepository userDetailRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.bookingService = bookingService;
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserResponseDTO registerUserDTODetails(UserRequestDTO userDTO) throws FlightNotFoundException {
        UserEntity user = userDTO.toEntityUserDetails();
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    @Override
    public Optional<UserResponseDTO> bookFlight(Long userid, UserRequestDTO userDTO) throws FlightNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(userid);
        if (optionalUser.isPresent()) {
            List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<>();
            for (BookingRequestDTO bookingDTO : userDTO.getBookingRequestDTO()) {
                BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
                bookingRequestDTO.setBookingStatusEnum(BookingStatusEnum.BOOKED);
                bookingRequestDTO.setFlights(bookingDTO.getFlights());
                bookingRequestDTOs.add(bookingRequestDTO);
            }
            for (BookingRequestDTO bookingRequestDTO : bookingRequestDTOs) {
                bookingService.saveBooking(bookingRequestDTO, userid);
            }
        }
        return Optional.of(new UserResponseDTO(userDTO));
    }

    @Override
    public UserDetailDTOResponse findAllBookingsForTraveller(Long id) {
        UserDetailDTOResponse userDetailDTOResponse = new UserDetailDTOResponse();
        userDetailDTOResponse = userDetailDTOResponse.mapToDTOForTraveller(userDetailRepository.findByUser(id));
        return userDetailDTOResponse;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findUserEntityByUserDetail_EmailContaining(username);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return toUserDetails(userEntity);
        }

        Optional<AdminEntity> adminEntityOptional = adminRepository.findByUserDetail_EmailContainingIgnoreCase(username);
        if (adminEntityOptional.isPresent()) {
            AdminEntity adminEntity = adminEntityOptional.get();
            return toUserDetailsAdmin(adminEntity);
        }

        throw new UsernameNotFoundException("Email not found");
    }


    private UserDetails toUserDetails(UserEntity user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserDetail().getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole().toString())
                .build();
    }

    private UserDetails toUserDetailsAdmin(AdminEntity user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserDetail().getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole().toString())
                .build();
    }


}
