package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
public class UserApp implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "The name must contain only letters")
    private String firstname;

    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Last name must contain only letters")
    private String lastname;

    @NotNull
    @Size(min = 10, max = 15)
    @Pattern(regexp = "\\d+", message = "The phone number must contain only numbers")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date")
    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<RoomReview> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public UserApp() {
        this.registrationDate = new Date(System.currentTimeMillis());
    }

    public UserApp(String email, String password, String firstname, String lastname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.registrationDate = new Date(System.currentTimeMillis());
    }

    public UserApp(Long userId, String email, String password, String firstname, String lastname, String phoneNumber, Date registrationDate, Role role, List<RoomReview> reviews, List<Reservation> reservations) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.role = role;
        this.reviews = reviews;
        this.reservations = reservations;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RoomReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RoomReview> reviews) {
        this.reviews = reviews;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                ", reviews=" + reviews +
                ", reservations=" + reservations +
                '}';
    }
}