package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.userDetail.UserDetailDTOResponse;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository <UserDetailEntity, Long> {

   UserDetailEntity findByEmail (String email);

   @Query("SELECT ud FROM UserDetailEntity ud WHERE ud.user.id = :id")
   UserDetailEntity findByUser(@Param("id") Long id);


}
