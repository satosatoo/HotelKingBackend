package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;                    //        impl

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "staff")
    @Column(name = "job_position")
    private JobPosition jobPosition;

    @NotBlank
    @Column(name = "working_days")
    private String workingDays;

    @NotBlank
    @Column(name = "working_time")
    private String workingTime;

    @NotNull
    @Column(name = "employment_date")
    private Date employmentDate;     // дата трудоустройства

    @OneToOne
    @JoinColumn(name = "personal_info_id")
    @Column(name = "personal_info")
    private PersonalInfo personalInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "staff_role_junction",
               joinColumns = {@JoinColumn(name = "employee_id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> authorities;

    public Employee(String email, String password, String name, String surname, String phoneNumber, JobPosition jobPosition, String workingDays, String workingTime, Date employmentDate, PersonalInfo personalInfo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.jobPosition = jobPosition;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.employmentDate = employmentDate;
        this.personalInfo = personalInfo;
    }

    public Employee(Long employeeId, String email, String password, String name, String surname, String phoneNumber, JobPosition jobPosition, String workingDays, String workingTime, Date employmentDate, PersonalInfo personalInfo) {
        this.employeeId = employeeId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.jobPosition = jobPosition;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.employmentDate = employmentDate;
        this.personalInfo = personalInfo;
    }

    public Employee(Long employeeId, String email, String password, String name, String surname, String phoneNumber, JobPosition jobPosition, String workingDays, String workingTime, Date employmentDate, PersonalInfo personalInfo, List<Role> authorities) {
        this.employeeId = employeeId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.jobPosition = jobPosition;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.employmentDate = employmentDate;
        this.personalInfo = personalInfo;
        this.authorities = authorities;
    }
}
