package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "Must accommodate at least 1 guest")
    private Integer numberOfGuests;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Room size is required")
    @Positive(message = "Room size must be positive")
    private Double roomSize;

    @NotNull(message = "Room type is required")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "cost_per_night")
    @NotNull(message = "Cost per night is required")
    @Positive(message = "Cost per night must be positive")
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
