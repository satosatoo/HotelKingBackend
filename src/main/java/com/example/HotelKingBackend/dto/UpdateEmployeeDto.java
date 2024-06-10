//package com.example.HotelKingBackend.dto;
//
//import com.example.HotelKingBackend.models.DayOfWeek;
//import jakarta.validation.constraints.NotBlank;
//import lombok.Data;
//
//import java.util.List;
//
//public class UpdateEmployeeDto {
//    @NotBlank
//    private String phoneNumber;
//
//    @NotBlank
//    private List<DayOfWeek> workingDays;
//
//    @NotBlank
//    private String workingTime;
//
//    @NotBlank
//    private String placeOfResidence;
//
//    @NotBlank
//    private Integer jobPositionId;
//
//    public UpdateEmployeeDto() {
//    }
//
//    public UpdateEmployeeDto(String phoneNumber, List<DayOfWeek> workingDays, String workingTime, String placeOfResidence, Integer jobPositionId) {
//        this.phoneNumber = phoneNumber;
//        this.workingDays = workingDays;
//        this.workingTime = workingTime;
//        this.placeOfResidence = placeOfResidence;
//        this.jobPositionId = jobPositionId;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public List<DayOfWeek> getWorkingDays() {
//        return workingDays;
//    }
//
//    public void setWorkingDays(List<DayOfWeek> workingDays) {
//        this.workingDays = workingDays;
//    }
//
//    public String getWorkingTime() {
//        return workingTime;
//    }
//
//    public void setWorkingTime(String workingTime) {
//        this.workingTime = workingTime;
//    }
//
//    public String getPlaceOfResidence() {
//        return placeOfResidence;
//    }
//
//    public void setPlaceOfResidence(String placeOfResidence) {
//        this.placeOfResidence = placeOfResidence;
//    }
//
//    public Integer getJobPositionId() {
//        return jobPositionId;
//    }
//
//    public void setJobPositionId(Integer jobPositionId) {
//        this.jobPositionId = jobPositionId;
//    }
//}
