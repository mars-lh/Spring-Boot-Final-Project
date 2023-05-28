package com.project.java.project.springboot.model.userDetail;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailDTORequest {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private Long phoneNumber;

    private RoleEnum userRole;

    private List<UserBookingsRequestDTO> userBookings = new ArrayList<>();

    private List<BookingRequestDTO> bookingRequestDTO =  new ArrayList<>();


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
        userDetail.setId(this.id);
        userDetail.setFirstName(this.firstName);
        userDetail.setLastName(this.lastName);
        userDetail.setMiddleName(this.middleName);
        userDetail.setEmail(this.email);
        userDetail.setPhoneNumber(this.phoneNumber);



        return userDetail;
    }

}
