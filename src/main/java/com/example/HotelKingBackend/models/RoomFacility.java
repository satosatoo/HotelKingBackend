package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class RoomFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private int facilityId;

    @NotNull(message = "Name cannot be null")
    private String name;

    @ManyToMany(mappedBy = "facilities")
    @JsonIgnore
    private List<Room> rooms;

    public RoomFacility() {
    }

    @JsonCreator
    public RoomFacility(@JsonProperty("name") String name) {
        this.name = name;
    }
}
