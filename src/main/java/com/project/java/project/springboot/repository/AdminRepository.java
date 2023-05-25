package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Long> {

    List<AdminEntity> findAll();

}
