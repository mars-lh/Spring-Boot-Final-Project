package com.project.java.project.springboot.model.admin;

import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.roles.RolesDTO;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDTO {

    private String username;

    private String password;

    private RoleEnum userRole;

    private RolesDTO rolesDTO;

    private UserDetailDTORequest userDetailDTO;



    public AdminDTO(AdminEntity admin) {
    }


    public AdminEntity toEntity() {
        AdminEntity admin = new AdminEntity();
        admin.setUsername(this.username);
        admin.setPassword(this.password);
        admin.setUserRole(this.userRole);

        // Create and associate a new RolesEntity
        RolesEntity roles = new RolesEntity();
        roles.setRole(this.userRole);
        admin.setRoles(roles);
        roles.setAdmin(admin);

        return admin;
    }


    public AdminEntity toEntityUserDetails() {

        AdminEntity admin = new AdminEntity();
        admin.setUsername(this.username);
        admin.setPassword(this.password);
        admin.setUserRole(this.userRole);

        // Create and associate a new RolesEntity
        RolesEntity roles = new RolesEntity();
        roles.setRole(this.userRole);
        admin.setRoles(roles);
        roles.setAdmin(admin);

        if (this.userDetailDTO != null) {
            UserDetailEntity userDetail = this.userDetailDTO.toEntity();
            admin.setUserDetail(userDetail);
            userDetail.setUserRole(admin.getUserRole());
            userDetail.setAdminUser(admin);
        }

        return admin;
    }

}


