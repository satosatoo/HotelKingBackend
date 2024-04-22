package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Email(message = "Invalid email format")
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;

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

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Boolean policyAcknowledged, String firstname, String lastname, String phoneNumber, String comment, String userEmail, List<Extra> extras, Payment payment) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.policyAcknowledged = policyAcknowledged;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.userEmail = userEmail;
        this.extras = extras;
        this.payment = payment;
    } //Required request parameter 'roomId' for method parameter type int is not present]
}