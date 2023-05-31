package com.project.java.project.springboot.model.userDetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailDTOResponse {

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private Long phoneNumber;

    private RoleEnum userRole;

    private List<UserBookingsResponseDTO> userBookings = new ArrayList<>();

    public UserDetailDTOResponse mpaDTO (UserDetailEntity userDetail) {
        UserDetailDTOResponse userDetailDTOResponse = new UserDetailDTOResponse();
        userDetailDTOResponse.setFirstName(userDetail.getFirstName());
        userDetailDTOResponse.setMiddleName(userDetail.getMiddleName());
        userDetailDTOResponse.setLastName(userDetail.getLastName());
        userDetailDTOResponse.setEmail(userDetail.getEmail());
        userDetailDTOResponse.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailDTOResponse.setUserRole(userDetail.getUserRole());
        List<UserBookingsResponseDTO> userBookingsDTO = convertToUserBookingsResponseDTO(userDetail.getUserBookings());
        userDetailDTOResponse.setUserBookings(userBookingsDTO);
        return userDetailDTOResponse;
    }

    private List<UserBookingsResponseDTO> convertToUserBookingsResponseDTO(List<UserBookingsEntity> userBookings) {
        List<UserBookingsResponseDTO> userBookingsDTO = new ArrayList<>();
        for (UserBookingsEntity userBooking : userBookings) {
            UserBookingsResponseDTO userBookingDTO = new UserBookingsResponseDTO(userBooking);
            userBookingsDTO.add(userBookingDTO);
        }
        return userBookingsDTO;
    }
}
