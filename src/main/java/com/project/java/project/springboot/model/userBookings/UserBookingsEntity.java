package com.project.java.project.springboot.model.userBookings;

import com.project.java.project.springboot.model.bookings.BookingEntity;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_bookings")
public class UserBookingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDetail_id")
    private UserDetailEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatusEnum bookingStatus;

}
