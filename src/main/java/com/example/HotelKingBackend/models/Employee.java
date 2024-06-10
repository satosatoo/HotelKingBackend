//package com.example.HotelKingBackend.models;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Data
//@EntityListeners(AuditingEntityListener.class)
//public class Employee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long employeeId;
//
//    @NotBlank
//    @Size(min = 2, max = 50)
//    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "The name must contain only letters")
//    private String firstname;
//
//    @NotBlank
//    @Size(min = 2, max = 50)
//    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Last name must contain only letters")
//    private String lastname;
//
//    @Column(name = "phone_number", unique = true)
//    private String phoneNumber;
//
//    @CreatedDate
//    @Column(nullable = false, updatable = false, name = "registration_date")
//    private Date registrationDate;
//
//    @Enumerated(EnumType.STRING)
//    private List<DayOfWeek> workingDays;
//
//    @NotNull
//    private String workingTime;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dob;
//
//    @NotNull
//    private String placeOfResidence;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "position_id")
//    private JobPosition job_position;
//
//    public Employee() {}
//
//    public Employee(String firstname, String lastname, String phoneNumber, List<DayOfWeek> workingDays, String workingTime, Date dob, String placeOfResidence, JobPosition job_position) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.phoneNumber = phoneNumber;
//        this.workingDays = workingDays;
//        this.workingTime = workingTime;
//        this.dob = dob;
//        this.placeOfResidence = placeOfResidence;
//        this.job_position = job_position;
//    }
//
//    @PrePersist
//    @PreUpdate
//    private void validate() {
//        // Working days validation
//        if (workingDays != null && (workingDays.size() < 3 || workingDays.size() > 5)) {
//            throw new IllegalArgumentException("Working days must be between 3 and 5");
//        }
//
//        // Working time validation
//        if (workingTime != null) {
//            // Split the working time string into start and end hours
//            String[] hours = workingTime.split("-");
//            if (hours.length == 2) {
//                int startHour = Integer.parseInt(hours[0].substring(0, 2));
//                int endHour = Integer.parseInt(hours[1].substring(0, 2));
//                if (endHour - startHour > 8) {
//                    throw new IllegalArgumentException("Working hours cannot exceed 8 hours");
//                }
//            } else {
//                throw new IllegalArgumentException("Invalid working time format");
//            }
//        }
//
//        // Date of birth validation
//        if (dob != null) {
//            // Calculate age
//            long currentTime = System.currentTimeMillis();
//            long ageInMillis = currentTime - dob.getTime();
//            long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
//            if (ageInYears < 18) {
//                throw new IllegalArgumentException("Employee must be at least 18 years old");
//            }
//        }
//    }
//}