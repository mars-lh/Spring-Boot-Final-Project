package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.roles.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <RolesEntity, Long> {

}
