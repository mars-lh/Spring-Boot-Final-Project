package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {


    Optional<UserEntity> findUserEntityByUserDetail_EmailContaining (String username);
}
