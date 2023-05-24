package com.project.java.project.springboot.model.user;

import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.roles.RolesDTO;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTORequest;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTORequest {

    private String username;

    private String password;

    private RoleEnum userRole;

    private RolesDTO rolesDTO;

    private UserDetailDTORequest userDetailDTO;



    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setUserRole(this.userRole);

        // Create and associate a new RolesEntity
        RolesEntity roles = new RolesEntity();
        roles.setRole(this.userRole);
        user.setRoles(roles);
        roles.setUser(user);

        return user;
    }

    public UserEntity toEntityUserDetails() {
        UserEntity user = new UserEntity();
        user.setUsername(this.username);
        user.setPassword(this.password);
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






