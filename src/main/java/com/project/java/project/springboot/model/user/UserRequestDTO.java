package com.project.java.project.springboot.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
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

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {

    private Long id;

    private String username;

    private String password;

    private RoleEnum userRole;

    private RolesRequestDTO rolesRequestDTO;

    private UserDetailDTORequest userDetailDTO;

    private List<BookingRequestDTO> bookingRequestDTO = new ArrayList<>();


    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setId(this.id);
        user.setUsername(this.username);
        String hashedPassword = hashPassword(this.password);
        user.setPassword(hashedPassword);
        user.setUserRole(this.userRole);

        // Create and associate a new RolesEntity
        RolesEntity roles = new RolesEntity();
        roles.setRole(this.userRole);
        user.setRoles(roles);
        roles.setUser(user);

        return user;
    }

    private String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    public UserEntity toEntityUserDetails() {
        UserEntity user = new UserEntity();
        user.setId(this.id);
        user.setUsername(this.username);
        String hashedPassword = hashPassword(this.password);
        user.setPassword(hashedPassword);
        user.setUserRole(this.userRole);

        // Create and associate a new RolesEntity
        RolesEntity roles = new RolesEntity();
        roles.setRole(this.userRole);
        user.setRoles(roles);
        roles.setUser(user);

        if (this.userDetailDTO != null) {
            UserDetailEntity userDetail = this.userDetailDTO.toEntity();
            user.setUserDetail(userDetail);
            userDetail.setUserRole(user.getUserRole());
            userDetail.setUser(user);
        }

        return user;
    }

}






