package com.project.java.project.springboot.model.roles;

import com.project.java.project.springboot.model.enums.RoleEnum;
import lombok.Data;

@Data
public class RolesDTO {


    private RoleEnum role;
    private String description;


    public RolesDTO(RolesEntity role) {
        this.role = role.getRole();
        this.description = role.getDescription();
    }

    public RolesEntity toEntity() {
        RolesEntity role = new RolesEntity();

        role.setRole(this.getRole());
        if (this.getRole() == RoleEnum.ROLE_TRAVELLER) {
            role.setDescription("User Privileges");}
        else {
            role.setDescription("Admin Privileges");
        }
        return role;
    }
}
