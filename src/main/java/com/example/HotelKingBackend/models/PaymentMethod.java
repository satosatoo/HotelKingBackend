package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "method_id")
    private int methodId;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private List<ReservationPayment> reservationPayments;

    public PaymentMethod(String name) {
        this.name = name;
    }
}
