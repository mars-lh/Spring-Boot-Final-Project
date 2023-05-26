package com.project.java.project.springboot.model.user;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import jakarta.persistence.*;


@Entity
@Table (name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String username;

    @Column(name = "password")
    private  String password;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private UserDetailEntity userDetail;



    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private RolesEntity roles;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private RoleEnum userRole;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetailEntity getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailEntity userDetail) {
        this.userDetail = userDetail;
    }

    public RolesEntity getRoles() {
        return roles;
    }

    public void setRoles(RolesEntity roles) {
        this.roles = roles;
    }

    public RoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleEnum userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
