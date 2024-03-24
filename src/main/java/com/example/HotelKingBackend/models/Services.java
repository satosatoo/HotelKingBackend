package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "services")
    private List<Reservation> reservations;

    public Services(String name) {
        this.name = name;
    }
}