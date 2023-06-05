package com.project.java.project.springboot.model.roles;

import com.project.java.project.springboot.model.enums.RoleEnum;
import lombok.Data;

@Data
public class RolesRequestDTO {


    private RoleEnum role;
    private String description;


    public RolesRequestDTO(RolesEntity role) {
        this.role = role.getRole();
        this.description = role.getDescription();
    }

    public RolesEntity toEntity() {
        RolesEntity role = new RolesEntity();

        role.setRole(this.getRole());
        if (this.getRole() == RoleEnum.ROLE_TRAVELLER) {
            role.setDescription("User Privileges");
        } else {
            role.setDescription("Admin Privileges");
        }
        return role;
    }
}
