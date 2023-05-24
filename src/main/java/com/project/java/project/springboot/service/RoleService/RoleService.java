package com.project.java.project.springboot.service.RoleService;

import com.project.java.project.springboot.model.roles.RolesEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RolesEntity registerRole (RolesEntity role);
}
