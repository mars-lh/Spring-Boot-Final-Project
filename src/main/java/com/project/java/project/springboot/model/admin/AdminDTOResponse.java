package com.project.java.project.springboot.model.admin;

import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTOResponse {

    private String username;

    private RoleEnum role;

    private UserDetailDTOResponse userDetail;


    public AdminDTOResponse(AdminDTO admin ) {
       this.username = admin.getUsername();
        this.role = admin.getUserRole();
    }

    public AdminDTOResponse(AdminEntity admin) {
        this.username = admin.getUsername();
        this.role = admin.getUserRole();
        if (admin.getUserDetail() != null) {
        this.userDetail = new UserDetailDTOResponse(admin.getUserDetail());}
    }
}
