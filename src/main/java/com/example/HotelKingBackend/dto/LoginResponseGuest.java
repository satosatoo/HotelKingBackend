package com.example.HotelKingBackend.dto;

import com.example.HotelKingBackend.models.Guest;

public class LoginResponseGuest extends LoginResponse {

    private Guest guest;

    public LoginResponseGuest(String jwt, Guest guest) {
        super(jwt);
        this.guest = guest;

    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
