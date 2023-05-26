package com.project.java.project.springboot.model.admin;

import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "admin_users")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private  String password;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "adminUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private UserDetailEntity userDetail;

    @OneToOne(mappedBy = "admin", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private RolesEntity roles;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private RoleEnum userRole;

    public void setRoles(RolesEntity roles) {
        this.roles = roles;
        if (roles != null) {
            roles.setAdmin(this);
        }
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
