package com.project.java.project.springboot.controller.RestRole;

import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.service.RoleService.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/saveRole")
    public ResponseEntity<RolesEntity> saveRoles (@RequestBody RolesEntity roles) {
        return ResponseEntity.status(200).body(roleService.registerRole(roles));
    }
}
