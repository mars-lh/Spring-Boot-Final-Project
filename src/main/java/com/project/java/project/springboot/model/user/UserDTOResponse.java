package com.project.java.project.springboot.model.user;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTOResponse {

    private String username;

    private RoleEnum role;


    public UserDTOResponse (UserDTORequest user ) {
        this.username = user.getUsername();
        this.role = user.getUserRole();
    }

    public UserDTOResponse(UserEntity userEntity) {
    }
}
