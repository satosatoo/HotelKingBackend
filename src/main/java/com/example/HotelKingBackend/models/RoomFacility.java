package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class RoomFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private int facilityId;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "facilities")
    private List<Room> rooms;

    public RoomFacility(String name) {
        this.name = name;
    }
}
