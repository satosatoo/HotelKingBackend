package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Extra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_id")
    private int extraId;

    @NotBlank(message = "Name is required")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Description is required")
    private String location;

    @NotBlank(message = "Description is required")
    private String hours;

    public Extra() {
    }

    public Extra(String name, String description, String location, String hours) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.hours = hours;
    }
}