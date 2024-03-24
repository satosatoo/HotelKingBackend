package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class ReservationPayment {

    @Id
    @Column(name = "payment_id")
    private Long paymentId;                  //         impl

    @NotBlank
    @Column(name = "total_amount_due")
    private double totalAmountDue;

    @ManyToOne
    @JoinColumn(name = "method_id")
    private PaymentMethod paymentMethod;

    @NotNull
    private Boolean privacyAndTermsOfService;

    @NotNull
    private Boolean isPaid;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
