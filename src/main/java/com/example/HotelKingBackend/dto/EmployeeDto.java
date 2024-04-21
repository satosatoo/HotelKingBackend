package com.example.HotelKingBackend.dto;

import com.example.HotelKingBackend.models.DayOfWeek;
import com.example.HotelKingBackend.models.Gender;

import java.util.Date;
import java.util.List;

public class EmployeeDto {

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private List<DayOfWeek> workingDays;

    private String workingTime;

    private Date dob;

    private String placeOfResidence;

    private Gender gender;

    private Integer jobPositionId;

    public EmployeeDto(String email, String password, String firstname, String lastname, String phoneNumber, List<DayOfWeek> workingDays, String workingTime, Date dob, String placeOfResidence, Gender gender, Integer jobPositionId) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.dob = dob;
        this.placeOfResidence = placeOfResidence;
        this.gender = gender;
        this.jobPositionId = jobPositionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<DayOfWeek> workingDays) {
        this.workingDays = workingDays;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }
}
