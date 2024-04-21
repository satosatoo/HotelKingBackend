package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(nullable = false)
    @NotNull(message = "Check in date required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkInDate;

    @Column(nullable = false)
    @NotNull(message = "Check out date required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkOutDate;

    @Column(name = "total_cost")
    private double totalCost;

    @Column(nullable = false)
    @AssertTrue(message = "Please acknowledge policy")
    private Boolean policyAcknowledged;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String comment;

    @Email
    @Column(nullable = false, name = "guest_email")
    private String guestEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp userApp;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany
    @JoinTable(name = "reservation_extra",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "extra_id")})
    private List<Extra> extras;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public Reservation() {
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Boolean policyAcknowledged, String firstName, String surname, String phoneNumber, String comment, String guestEmail, List<Extra> extras) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.policyAcknowledged = policyAcknowledged;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.guestEmail = guestEmail;
        this.extras = extras;
    }
}