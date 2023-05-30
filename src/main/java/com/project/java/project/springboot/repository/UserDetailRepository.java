package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository <UserDetailEntity, Long> {

   UserDetailEntity findByEmail (String email);


}
