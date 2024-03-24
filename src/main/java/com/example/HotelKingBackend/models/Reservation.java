package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    private Long reservationId;                  // imp

    @NotBlank
    private Date checkinDate;

    @NotBlank
    private Date checkoutDate;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany
    @JoinTable(name = "reservation_service",
               joinColumns = {@JoinColumn(name = "reservation_id")},
               inverseJoinColumns = {@JoinColumn(name = "service_id")})
    private List<Services> services;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private ReservationPayment reservationPayment;
}
