package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.Month;
import java.time.Year;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @NotNull(message = "Credit card type is required")
    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;

    @NotNull(message = "Card number is required")
    @Column(nullable = false, name = "card_number")
    private String cardNumber;

    @Pattern(regexp = "[0-9]{3}", message = "CVV must be a 3-digit number")
    @NotNull(message = "CVV is required")
    private String cvv;

    @NotNull(message = "Holder name is required")
    @Column(nullable = false, name = "holder_name")
    private String holderName;

    @NotNull(message = "Card expiry year is required")
    private Year cardExpiryYear;

    @NotNull(message = "Card expiry month is required")
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
