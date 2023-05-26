package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Long> {

    List<AdminEntity> findAll();


    Optional<AdminEntity> findByUsername(String username);
}
