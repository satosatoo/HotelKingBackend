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
}
