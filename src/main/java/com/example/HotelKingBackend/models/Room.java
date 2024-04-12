package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @NotBlank
    private String name;

    @Column(name = "number_of_guests", nullable = false)
    @Min(value = 1, message = "Must accommodate at least 1 guest.")
    private int numberOfGuests;

    @NotBlank
    private String description;

    @NotNull
    @Column(name = "room_size")
    private Double roomSize;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @NotNull
    @Column(name = "price_per_night")
    private double costPerNight;

    @OneToMany(mappedBy = "room")
    private List<RoomReview> reviews;

    @ManyToMany
    @JoinTable(name = "room_facility_junction",
               joinColumns = {@JoinColumn(name = "room_id")},
               inverseJoinColumns = {@JoinColumn(name = "facility_id")})
    private List<RoomFacility> facilities;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<Reservation> reservations;

    public Room() {
    }

    public Room(String name, int numberOfGuests, String description, double roomSize, RoomType roomType, double costPerNight, List<RoomFacility> facilities) {
        this.name = name;
        this.numberOfGuests = numberOfGuests;
        this.description = description;
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.costPerNight = costPerNight;
        this.facilities = facilities;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    public double getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(double costPerNight) {
        this.costPerNight = costPerNight;
    }

    public List<RoomReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RoomReview> reviews) {
        this.reviews = reviews;
    }

    public List<RoomFacility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<RoomFacility> facilities) {
        this.facilities = facilities;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
