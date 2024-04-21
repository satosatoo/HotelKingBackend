package com.example.HotelKingBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class RoomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_rating_id")
    private Long roomReviewId;

    @NotNull
    private double rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    @JsonIgnore
    private UserApp userApp;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    public RoomReview(double rating, String comment, UserApp userApp, Room room) {
        this.rating = rating;
        this.comment = comment;
        this.room = room;
    }
}
