package com.project.java.project.springboot.model.userDetail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "user_detail")
public class UserDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToOne

    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private AdminEntity adminUser;

    @Column(name = "first_name")
    private String  firstName;

    @Column(name = "middle_name")
    private String middleName = "";

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address", unique = true)
    private String email;

    @JsonIgnore
    @Column (name = "phone_number")
    private Long phoneNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private RoleEnum userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBookingsEntity> userBookings = new ArrayList<>();

    @Override
    public String toString() {
        return "UserDetailEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userRole=" + userRole +
                '}';
    }
}
