package com.example.HotelKingBackend.dto;

public class UpdateUserDto {

    private String password;

    private String phoneNumber;

    public UpdateUserDto() {
    }

    public UpdateUserDto(String password, String phoneNumber) {
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
