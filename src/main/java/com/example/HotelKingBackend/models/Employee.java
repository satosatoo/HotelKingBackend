package com.example.HotelKingBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "The name must contain only letters")
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Last name must contain only letters")
    private String lastname;

    @NotBlank
    @Size(min = 10, max = 15)
    @Pattern(regexp = "\\d+", message = "The phone number must contain only numbers")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Enumerated(EnumType.STRING)                       // тут также делать делать доп проверку
    private List<DayOfWeek> workingDays;

    @NotNull                                           // принимать строку времени, переводить в числа и потом
    private String workingTime;                        // проверять не больше ли 8 часов указано

    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;

    @NotNull
    private String placeOfResidence;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private JobPosition job_position;

    public Employee() {
        this.registrationDate = new Date(System.currentTimeMillis());
    }

    public Employee(String firstname, String lastname, String phoneNumber, List<DayOfWeek> workingDays, String workingTime, Date dob, String placeOfResidence, JobPosition job_position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.workingDays = workingDays;
        this.workingTime = workingTime;
        this.dob = dob;
        this.placeOfResidence = placeOfResidence;
        this.job_position = job_position;
        this.registrationDate = new Date(System.currentTimeMillis());
    }

    @PrePersist
    @PreUpdate
    private void validate() {
        // Working days validation
        if (workingDays != null && (workingDays.size() < 3 || workingDays.size() > 5)) {
            throw new IllegalArgumentException("Working days must be between 3 and 5");
        }

        // Working time validation
        if (workingTime != null) {
            // Split the working time string into start and end hours
            String[] hours = workingTime.split("-");
            if (hours.length == 2) {
                int startHour = Integer.parseInt(hours[0].substring(0, 2));
                int endHour = Integer.parseInt(hours[1].substring(0, 2));
                if (endHour - startHour > 8) {
                    throw new IllegalArgumentException("Working hours cannot exceed 8 hours");
                }
            } else {
                throw new IllegalArgumentException("Invalid working time format");
            }
        }

        // Date of birth validation
        if (dob != null) {
            // Calculate age
            long currentTime = System.currentTimeMillis();
            long ageInMillis = currentTime - dob.getTime();
            long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            if (ageInYears < 18) {
                throw new IllegalArgumentException("Employee must be at least 18 years old");
            }
        }
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public JobPosition getJob_position() {
        return job_position;
    }

    public void setJob_position(JobPosition job_position) {
        this.job_position = job_position;
    }
}