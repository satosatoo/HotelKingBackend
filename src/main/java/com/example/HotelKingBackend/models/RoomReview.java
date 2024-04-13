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
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    public RoomReview(double rating, String comment, Guest guest, Room room) {
        this.rating = rating;
        this.comment = comment;
        this.room = room;
    }

    public Long getRoomReviewId() {
        return roomReviewId;
    }

    public void setRoomReviewId(Long roomReviewId) {
        this.roomReviewId = roomReviewId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
