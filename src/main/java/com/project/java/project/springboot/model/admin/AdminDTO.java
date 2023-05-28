package com.project.java.project.springboot.model.admin;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.bookings.BookingRequestDTO;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.roles.RolesRequestDTO;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDTO {

    private Long id;

    private String username;

    private String password;

    private RoleEnum userRole;

    private RolesRequestDTO rolesRequestDTO;

    private UserDetailDTORequest userDetailDTO;

    private BookingRequestDTO bookingRequestDTO;



    public AdminDTO(AdminEntity admin) {
    }


    public AdminEntity toEntity() {
        AdminEntity admin = new AdminEntity();
        admin.setId(this.id);
        admin.setUsername(this.username);
        String hashedPassword = hashPassword(this.password);
        admin.setPassword(hashedPassword);
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
        String hashedPassword = hashPassword(this.password);
        admin.setPassword(hashedPassword);
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

    private String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }




}


