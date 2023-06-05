package com.project.java.project.springboot.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.project.springboot.model.bookings.BookingResponseDTO;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {

    private String username;

    private RoleEnum role;

    @JsonIgnore
    private BookingResponseDTO bookingResponseDTO;

    private UserDetailDTOResponse userDetail = new UserDetailDTOResponse();


    public UserResponseDTO(UserRequestDTO user) {
        this.username = user.getUsername();
        this.role = user.getUserRole();
    }

    public UserResponseDTO(BookingResponseDTO bookingResponseDTO) {
        this.bookingResponseDTO = bookingResponseDTO;
    }

    public UserResponseDTO(UserDetailEntity userEntity) {
        this.userDetail = userDetail.mapToDTO(userEntity);
    }

    public UserResponseDTO(UserEntity user) {
        this.username = user.getUsername();
        this.role = user.getUserRole();
        this.userDetail = userDetail.mapToDTO(user.getUserDetail());
    }
}
