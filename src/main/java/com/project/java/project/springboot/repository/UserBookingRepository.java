package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookingRepository extends JpaRepository <UserBookingsEntity, Long> {


}
