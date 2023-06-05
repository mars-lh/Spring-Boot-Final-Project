package com.project.java.project.springboot.model.bookings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.java.project.springboot.model.enums.BookingStatusEnum;
import com.project.java.project.springboot.model.flights.FlightsRequestDTO;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsRequestDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequestDTO {

    private Long id;

    private Date bookingDate;

    private BookingStatusEnum bookingStatusEnum;

    private List<UserBookingsRequestDTO> userBookings = new ArrayList<>();

    @JsonProperty("flights")
    private FlightsRequestDTO flights;


    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();
        booking.setId(this.id);
        booking.setBookingStatus(BookingStatusEnum.BOOKED);
        booking.setBookingDate(new Date());
        List<UserBookingsEntity> userBookingsEntities = new ArrayList<>();
        for (UserBookingsRequestDTO userBookingDTO : this.userBookings) {
            UserBookingsEntity userBookingsEntity = userBookingDTO.toEntity();
            userBookingsEntities.add(userBookingsEntity);
        }
        return booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatusEnum getBookingStatusEnum() {
        return bookingStatusEnum;
    }

    public void setBookingStatusEnum(BookingStatusEnum bookingStatusEnum) {
        this.bookingStatusEnum = bookingStatusEnum;
    }

    public List<UserBookingsRequestDTO> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<UserBookingsRequestDTO> userBookings) {
        this.userBookings = userBookings;
    }

    public FlightsRequestDTO getFlights() {
        return flights;
    }

    public void setFlights(FlightsRequestDTO flights) {
        this.flights = flights;
    }
}
