package com.project.java.project.springboot.model.userDetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
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

    private List<UserBookingsEntity> userBookings = new ArrayList<>();



    public UserDetailDTOResponse (UserDetailEntity userDetail) {
        this.firstName =userDetail.getFirstName();
        this.middleName = userDetail.getMiddleName();
        this.lastName = userDetail.getLastName();
        this.email = userDetail.getEmail();
        this.phoneNumber = userDetail.getPhoneNumber();
        this.userRole = userDetail.getUserRole();
        this.userBookings = userDetail.getUserBookings().stream().toList();
    }

    public UserDetailDTOResponse mpaDTO (UserDetailEntity userDetail) {
        UserDetailDTOResponse userDetailDTOResponse = new UserDetailDTOResponse();
        userDetailDTOResponse.setFirstName(userDetail.getFirstName());
        userDetailDTOResponse.setMiddleName(userDetail.getMiddleName());
        userDetailDTOResponse.setLastName(userDetail.getLastName());
        userDetailDTOResponse.setEmail(userDetail.getEmail());
        userDetailDTOResponse.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailDTOResponse.setUserRole(userDetail.getUserRole());
//        userDetailDTOResponse.setUserBookings(userDetail.getUserBookings().stream().toList());
        return userDetailDTOResponse;
    }
}
