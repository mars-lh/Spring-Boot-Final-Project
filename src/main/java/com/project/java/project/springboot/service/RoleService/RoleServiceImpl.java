package com.project.java.project.springboot.service.RoleService;

import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public RolesEntity registerRole(RolesEntity role) {
        return roleRepository.save(role);
    }
}
