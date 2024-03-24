package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Room {

    @Id
    @Column(name = "room_id")
    private int roomId;

    @NotBlank
    private String name;

    @Column(name = "number_of_guests")
    @Min(value = 1, message = "Must accommodate at least 1 guest.")
    private int numberOfGuests;

    @NotBlank
    private String description;

    @NotBlank
    @Column(name = "room_size")
    private double roomSize;

    @NotBlank
    private double rating;

    @Column(name = "room_facility")
    @ManyToMany
    @JoinTable(name = "room_facility",
               joinColumns = {@JoinColumn(name = "room_id")},
               inverseJoinColumns = {@JoinColumn(name = "facility_id")})
    private List<RoomFacility> roomFacility;

    @NotBlank
    @Column(name = "price_per_night")
    private double pricePerNight;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public Room(int roomId, String name, int numberOfGuests, String description, double roomSize, List<RoomFacility> roomFacility, double pricePerNight) {
        this.roomId = roomId;
        this.name = name;
        this.numberOfGuests = numberOfGuests;
        this.description = description;
        this.roomSize = roomSize;
        this.roomFacility = roomFacility;
        this.pricePerNight = pricePerNight;
    }

    public Room(String name, int numberOfGuests, String description, double roomSize, List<RoomFacility> roomFacility, double pricePerNight) {
        this.name = name;
        this.numberOfGuests = numberOfGuests;
        this.description = description;
        this.roomSize = roomSize;
        this.roomFacility = roomFacility;
        this.pricePerNight = pricePerNight;
    }


}
