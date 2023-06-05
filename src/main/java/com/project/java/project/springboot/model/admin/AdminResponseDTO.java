package com.project.java.project.springboot.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminResponseDTO {

    private String username;

    @JsonIgnore
    private RoleEnum role;

    private UserDetailDTOResponse userDetail = new UserDetailDTOResponse();


    public AdminResponseDTO(AdminEntity admin) {
        this.username = admin.getUsername();

        if (admin.getUserDetail() != null) {
            this.userDetail = userDetail.mapToDTO(admin.getUserDetail());
        }
    }

    public AdminResponseDTO(UserEntity userEntity) {
        this.username = userEntity.getUsername();

        if (userEntity.getUserDetail() != null) {
            this.userDetail = userDetail.mapToDTO(userEntity.getUserDetail());
        }
    }



}
