package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private int genderId;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "gender")
    @Column(name = "personal_info")
    private List<PersonalInfo> personalInfo;

    public Gender(String name) {
        this.name = name;
    }
}
