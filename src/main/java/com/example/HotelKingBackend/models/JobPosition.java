package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionId;

    @NotBlank
    private String name;

    @NotNull
    private double salary;

    @OneToMany(mappedBy = "job_position")
    @JsonIgnore
    private List<Employee> employees;

    public JobPosition(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
