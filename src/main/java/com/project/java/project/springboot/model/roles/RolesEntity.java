package com.project.java.project.springboot.model.roles;

import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "roles")
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private AdminEntity admin;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "roles")
    private RoleEnum role;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RolesEntity{" +
                "id=" + id +
                ", admin=" + admin +
                ", user=" + user +
                ", role=" + role +
                ", description='" + description + '\'' +
                '}';
    }
}
