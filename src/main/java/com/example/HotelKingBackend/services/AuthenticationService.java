//package com.example.HotelKingBackend.services;
//
//import com.example.HotelKingBackend.config.TokenService;
//import com.example.HotelKingBackend.dto.*;
//import com.example.HotelKingBackend.models.Employee;
//import com.example.HotelKingBackend.models.Guest;
//import com.example.HotelKingBackend.models.Role;
//import com.example.HotelKingBackend.repositories.EmployeeRepository;
//import com.example.HotelKingBackend.repositories.GuestRepository;
//import com.example.HotelKingBackend.repositories.ReservationRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class AuthenticationService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private GuestRepository guestRepository;
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenService tokenService;
//
//
//    public Guest registerGuest(Guest guest) {
//        String encodedPassword = passwordEncoder.encode(guest.getPassword());
//        Role authority = Role.USER;
//
//        guest.setRole(authority);
//        guest.setUserId(GuestService.getNextGuestId());
//        guest.setPassword(encodedPassword);
//
//        return guestRepository.save(guest);
//    }
//
//
//    public Employee registerEmployee(Employee employee) {
//        String encodedPassword = passwordEncoder.encode(employee.getPassword());
//        Role authority = Role.EMPLOYEE;
//
//        employee.setUserId(EmployeeService.getNextEmployeeId());
//        employee.setRole(authority);
//        employee.setPassword(encodedPassword);
//
//        return employeeRepository.save(employee);
//    }
//
//
//    public LoginResponse login(String email, String password) {
//        Authentication auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(email, password)
//        );
//        String token = tokenService.generateJwt(auth);
//
//        if (guestRepository.findByEmail(email).isPresent()) {
//            Guest guest = guestRepository.findByEmail(email)
//                    .orElseThrow(() -> new EntityNotFoundException("Guest was not found"));
//            return new LoginResponseGuest(token, guest);
//        } else {
//            Employee employee = employeeRepository.findByEmail(email)
//                    .orElseThrow(() -> new EntityNotFoundException("Employee was not found"));
//            return new LoginResponseEmployee(token, employee);
//        }
//    }
//}
