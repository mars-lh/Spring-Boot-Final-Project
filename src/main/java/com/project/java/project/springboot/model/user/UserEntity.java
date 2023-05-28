package com.project.java.project.springboot.model.user;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.FlightStatusEnum;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
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


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
