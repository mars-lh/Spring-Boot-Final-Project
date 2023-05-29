package com.project.java.project.springboot.model.bookings;

import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsEntity;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userDetail.UserDetailEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private BookingStatusEnum bookingStatus;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<UserBookingsEntity> userBookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "flight_id", referencedColumnName = "id")
    private FlightsEntity flights;

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", bookingStatus=" + bookingStatus +
                ", flights=" + (flights != null ? flights.getFlightNumber() : null) +
                '}';
    }
}
