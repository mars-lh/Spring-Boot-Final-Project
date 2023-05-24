package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {


}
