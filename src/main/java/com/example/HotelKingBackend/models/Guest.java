package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
public class Guest {

    @Id
    @Column(name = "guest_id")
    private Long guestId;                // реализовать правильное инкрементирование  mb uuid

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank
    private String name;               //        validation

    @NotBlank
    private String surname;            //        validation

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date")
    private Date registrationDate;

//    public void save(User user) {
//        user.setRegistrationDate(new Date()); Установка текущей даты регистрации
//        userRepository.save(user);
//    }

    @OneToMany(mappedBy = "guest")
    private List<RoomReview> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "guest_role_junction",
               joinColumns = {@JoinColumn(name = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> authorities;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Guest() {
    }

    public Guest(Long guestId, String email, String password, String name, String surname, String phoneNumber, Date registrationDate) {
        this.guestId = guestId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    public Guest(String email, String password, String name, String surname, String phoneNumber, Date registrationDate, List<Role> authorities, List<Reservation> reservations) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.authorities = authorities;
        this.reservations = reservations;
    }

    public Guest(Long guestId, String email, String password, String name, String surname, String phoneNumber, Date registrationDate, List<Role> authorities, List<Reservation> reservations) {
        this.guestId = guestId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.authorities = authorities;
        this.reservations = reservations;
    }
}