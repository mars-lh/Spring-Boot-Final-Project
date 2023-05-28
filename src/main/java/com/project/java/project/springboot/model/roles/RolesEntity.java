package com.project.java.project.springboot.model.roles;

import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
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

    @Override
    public String toString() {
        return "RolesEntity{" +
                "id=" + id +
                ", admin=" + admin +
                ", user=" + user +
                ", description='" + description + '\'' +
                '}';
    }
}
