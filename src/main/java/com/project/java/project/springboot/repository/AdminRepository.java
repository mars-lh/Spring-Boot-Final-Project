package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {

    List<AdminEntity> findAll();

}
