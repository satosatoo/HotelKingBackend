package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Month;
import java.time.Year;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;

    @Column(nullable = false, name = "card_number")
    private String cardNumber;

    @Pattern(regexp = "[0-9]{3}", message = "3 digit number expected")
    @NotNull
    private String cvv;

    @Column(nullable = false, name = "holder_name")
    private String holderName;

    @NotNull
    private Year cardExpiryYear;

    @NotNull
    private Month cardExpiryMonth;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Payment() {
    }

    public Payment(CreditCardType creditCardType, String cardNumber, String cvv, String holderName, Year cardExpiryYear, Month cardExpiryMonth) {
        this.creditCardType = creditCardType;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.holderName = holderName;
        this.cardExpiryYear = cardExpiryYear;
        this.cardExpiryMonth = cardExpiryMonth;
    }
}
