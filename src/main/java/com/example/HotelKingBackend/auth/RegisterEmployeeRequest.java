package com.example.HotelKingBackend.auth;

import com.example.HotelKingBackend.models.DayOfWeek;
import com.example.HotelKingBackend.models.Gender;
import com.example.HotelKingBackend.models.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private List<DayOfWeek> workingDays;
    private String workingHours;
    private Date dob;
    private String placeOfResidence;
    private Gender gender;
    private JobPosition jobPosition;
}
