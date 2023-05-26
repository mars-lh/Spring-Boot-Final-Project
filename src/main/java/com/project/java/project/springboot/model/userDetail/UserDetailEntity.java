package com.project.java.project.springboot.model.userDetail;

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

    @Column (name = "phone_number")
    private Long phoneNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private RoleEnum userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBookingsEntity> userBookings = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AdminEntity getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminEntity adminUser) {
        this.adminUser = adminUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleEnum userRole) {
        this.userRole = userRole;
    }

    public List<UserBookingsEntity> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<UserBookingsEntity> userBookings) {
        this.userBookings = userBookings;
    }

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
