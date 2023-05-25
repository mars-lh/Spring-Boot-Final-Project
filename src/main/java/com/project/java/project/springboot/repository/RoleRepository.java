package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.roles.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <RolesEntity, Long> {

}
