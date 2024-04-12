//package com.example.HotelKingBackend.controllers;
//
//import com.example.HotelKingBackend.dto.LoginDto;
//import com.example.HotelKingBackend.dto.LoginResponse;
//import com.example.HotelKingBackend.models.Employee;
//import com.example.HotelKingBackend.models.Guest;
//import com.example.HotelKingBackend.services.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @PostMapping("/register-guest")
//    public Guest registerGuest(@RequestBody Guest guest) {
//        return authenticationService.registerGuest(guest);
//    }
//
//    @PostMapping("/register-employee")
//    public Employee registerEmployee(@RequestBody Employee employee) {
//        return authenticationService.registerEmployee(employee);
//    }
//
//    @PostMapping("/login")
//    public LoginResponse loginUser(@RequestBody LoginDto body) {
//        return authenticationService.login(body.getUsername(), body.getPassword());
//    }
//}
