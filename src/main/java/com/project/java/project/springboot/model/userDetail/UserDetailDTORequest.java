package com.project.java.project.springboot.model.userDetail;


import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailDTORequest {

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private Long phoneNumber;

    private RoleEnum userRole;

    private List<UserBookingsEntity> userBookings = new ArrayList<>();


    UserDetailDTORequest (UserDetailEntity userDetail) {
        this.firstName = userDetail.getFirstName();
        this.middleName = userDetail.getMiddleName();
        this.lastName = userDetail.getLastName();
        this.email = userDetail.getEmail();
        this.phoneNumber = userDetail.getPhoneNumber();
        this.userRole = userDetail.getUserRole();
    }

    public UserDetailEntity toEntity () {
        UserDetailEntity userDetail = new UserDetailEntity();
        userDetail.setFirstName(this.firstName);
        userDetail.setLastName(this.lastName);
        userDetail.setMiddleName(this.middleName);
        userDetail.setEmail(this.email);
        userDetail.setPhoneNumber(this.phoneNumber);
        return userDetail;
    }

}
