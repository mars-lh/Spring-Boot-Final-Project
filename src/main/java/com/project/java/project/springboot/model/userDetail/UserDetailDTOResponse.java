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

    public UserDetailDTOResponse(String firstName, String middleName, String lastName, String email, Long phoneNumber, RoleEnum userRole, List<UserBookingsEntity> userBookings) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.userBookings = userBookings;
    }





    public UserDetailDTOResponse(UserDetailEntity userDetail) {
        this.firstName =userDetail.getFirstName();
        this.middleName = userDetail.getMiddleName();
        this.lastName = userDetail.getLastName();
        this.email = userDetail.getEmail();
        this.phoneNumber = userDetail.getPhoneNumber();
        this.userRole = userDetail.getUserRole();
        this.userBookings = userDetail.getUserBookings();
    }
}
