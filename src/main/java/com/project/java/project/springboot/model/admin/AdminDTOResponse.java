package com.project.java.project.springboot.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminDTOResponse {

    private String username;

    @JsonIgnore
    private RoleEnum role;

    private UserDetailDTOResponse userDetail = new UserDetailDTOResponse();


    public AdminDTOResponse(AdminEntity admin) {
        this.username = admin.getUsername();

        if (admin.getUserDetail() != null) {
            this.userDetail = userDetail.mpaDTO(admin.getUserDetail());
        }
    }

    public AdminDTOResponse(UserEntity userEntity) {
        this.username = userEntity.getUsername();

        if (userEntity.getUserDetail() != null) {
            this.userDetail = userDetail.mpaDTO(userEntity.getUserDetail());
        }
    }
}
