package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private double cost;

    @ManyToMany(mappedBy = "extras")
    @JsonIgnore
    private List<Reservation> reservations;

    public Extra() {
    }

    public Extra(String name, String description, double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
}