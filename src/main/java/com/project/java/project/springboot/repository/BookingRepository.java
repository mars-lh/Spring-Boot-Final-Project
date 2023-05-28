package com.project.java.project.springboot.repository;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository <BookingEntity, Long> {
}
