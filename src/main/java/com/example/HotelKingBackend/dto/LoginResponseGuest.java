package com.example.HotelKingBackend.dto;

import com.example.HotelKingBackend.models.UserApp;

public class LoginResponseGuest extends LoginResponse {

    private UserApp userApp;

    public LoginResponseGuest(String jwt, UserApp userApp) {
        super(jwt);
        this.userApp = userApp;

    }

    public UserApp getGuest() {
        return userApp;
    }

    public void setGuest(UserApp userApp) {
        this.userApp = userApp;
    }
}
