package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class RoomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_review_id")
    private Long roomReviewId;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private double rating;

    @Size(max = 500, message = "Comment must be at most 500 characters")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserApp user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    public RoomReview() {
    }

    public RoomReview(double rating, String comment, UserApp userApp) {
        this.rating = rating;
        this.comment = comment;
    }
}
