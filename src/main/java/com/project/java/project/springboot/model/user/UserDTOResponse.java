package com.project.java.project.springboot.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.flights.FlightsDTORequest;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTOResponse {

    private String username;

    private RoleEnum role;

    private BookingResponseDTO bookingResponseDTO;

    private FlightsDTORequest flightsDTORequest;


    public UserDTOResponse (UserDTORequest user ) {
        this.username = user.getUsername();
        this.role = user.getUserRole();
    }

    public UserDTOResponse(UserBookingsEntity userEntity) {
    }

    public UserDTOResponse(UserEntity userEntity) {
    }
}
