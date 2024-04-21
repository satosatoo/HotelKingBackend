package com.example.HotelKingBackend.dto;

import com.example.HotelKingBackend.models.DayOfWeek;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class UpdateEmployeeDto {

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private List<DayOfWeek> workingDays;

    @NotBlank
    private String workingTime;

    @NotBlank
    private String placeOfResidence;

    @NotBlank
    private Integer jobPositionId;

    public UpdateEmployeeDto() {
    }

    public UpdateEmployeeDto(String password, String phoneNumber, List<DayOfWeek> workingDays, String workingTime, String placeOfResidence, Integer jobPositionId) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.placeOfResidence = placeOfResidence;
        this.jobPositionId = jobPositionId;
    }
}
