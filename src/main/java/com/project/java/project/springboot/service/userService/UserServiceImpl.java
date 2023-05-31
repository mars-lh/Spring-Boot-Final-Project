package com.project.java.project.springboot.service.userService;

import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.flights.FlightsDTOResponse;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.repository.AdminRepository;
import com.project.java.project.springboot.repository.FlightRepository;
import com.project.java.project.springboot.repository.UserBookingRepository;
import com.project.java.project.springboot.repository.UserRepository;
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
    private final FlightRepository flightRepository;
    private final BookingService bookingService;
    private final UserbookingService userbookingService;

    public UserServiceImpl(UserRepository userRepository, AdminRepository adminRepository, UserBookingRepository userBookingsRepository, FlightRepository flightRepository, BookingService bookingService, UserbookingService userbookingService) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.flightRepository = flightRepository;
        this.bookingService = bookingService;
        this.userbookingService = userbookingService;
    }


    @Override
    public List<UserDTOResponse> findAll() {
        return userRepository.findAll().stream().map(UserDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public UserDTOResponse registerUserDTODetails(UserDTORequest userDTO) throws FlightNotFoundException {
        UserEntity user = userDTO.toEntityUserDetails();
        userRepository.save(user);
        return new UserDTOResponse(userDTO);
    }

    @Override
    public Optional<UserDTOResponse> bookFlight(Long userid, UserDTORequest userDTO) throws FlightNotFoundException {
     Optional<UserEntity> optionalUser = userRepository.findById(userid);
        if (optionalUser.isPresent()){
            List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<>();
            for (BookingRequestDTO bookingDTO : userDTO.getBookingRequestDTO()) {
                BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
                bookingRequestDTO.setBookingStatusEnum(BookingStatusEnum.BOOKED);
                bookingRequestDTO.setFlights(bookingDTO.getFlights());
                // Set any other required fields of the bookingRequestDTO
                bookingRequestDTOs.add(bookingRequestDTO);
            }
            // Call the saveBooking method for each booking
            for (BookingRequestDTO bookingRequestDTO : bookingRequestDTOs) {
                bookingService.saveBooking(bookingRequestDTO, userid);
            }
        }  return Optional.of(new UserDTOResponse(userDTO)) ;
    }

    @Override
    public Optional<List<FlightsDTOResponse>> findFlights(FlightsDTORequest flightsDTORequest) {
        List<FlightsDTOResponse> DTOResult = new ArrayList<>();
           for (FlightsEntity flightTopass : flightRepository.findFlightsEntityByDestinationCountryAndOriginCountryAndFlightDate(flightsDTORequest.getDestinationCountry(), flightsDTORequest.getOriginCountry(), flightsDTORequest.getFlightDate())){
               FlightsDTOResponse newDTO = new FlightsDTOResponse(flightTopass);
               DTOResult.add(newDTO);
           }
           return  Optional.of(DTOResult);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return toUserDetails(userEntity);
        }

        Optional<AdminEntity> adminEntityOptional = adminRepository.findByUsername(username);
        if (adminEntityOptional.isPresent()) {
            AdminEntity adminEntity = adminEntityOptional.get();
            return toUserDetailsAdmin(adminEntity);
        }

        throw new UsernameNotFoundException("Username not found");
    }


    private UserDetails toUserDetails(UserEntity user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getUserRole().toString())
                .build();
    }

    private UserDetails toUserDetailsAdmin(AdminEntity user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getUserRole().toString())
                .build();
    }


}
