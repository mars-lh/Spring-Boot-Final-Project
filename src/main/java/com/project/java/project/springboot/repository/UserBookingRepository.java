package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBookingRepository extends JpaRepository <UserBookingsEntity, Long> {


    Optional<UserBookingsEntity> findById (Long id);

}
