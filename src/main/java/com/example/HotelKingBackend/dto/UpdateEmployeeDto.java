package com.example.HotelKingBackend.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateEmployeeDto {

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String workingDays;

    @NotBlank
    private String workingTime;

    @NotBlank
    private String placeOfResidence;

    @NotBlank
    private Integer jobPositionId;

    public UpdateEmployeeDto() {
    }

    public UpdateEmployeeDto(String password, String phoneNumber, String workingDays, String workingTime, String placeOfResidence, Integer jobPositionId) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.placeOfResidence = placeOfResidence;
        this.jobPositionId = jobPositionId;
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

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public Integer getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }
}
