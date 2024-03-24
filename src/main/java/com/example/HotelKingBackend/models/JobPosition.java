package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private double salary;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private List<Employee> employees;

    public JobPosition(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
