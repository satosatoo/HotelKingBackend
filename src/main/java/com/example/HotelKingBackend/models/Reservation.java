package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
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

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "The name must contain only letters")
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Last name must contain only letters")
    private String lastname;

    @NotBlank
    @Size(min = 10, max = 15)
    @Pattern(regexp = "\\d+", message = "The phone number must contain only numbers")
    @Column(name = "phone_number")
    private String phoneNumber;

    private String comment;

    @NotNull(message = "Card number is required")
    @Column(name = "card_number")
    private String cardNumber;

    @Pattern(regexp = "[0-9]{3}", message = "CVV must be a 3-digit number")
    @NotNull(message = "CVV is required")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;

    public Reservation() {
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Boolean policyAcknowledged, String firstname, String lastname, String phoneNumber, String cardNumber, String cvv) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.policyAcknowledged = policyAcknowledged;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Boolean policyAcknowledged, String firstname, String lastname, String phoneNumber, String comment, String cardNumber, String cvv) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.policyAcknowledged = policyAcknowledged;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }
}