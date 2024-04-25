package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.auth.RegisterUserRequest;
import com.example.HotelKingBackend.dto.UpdateUserDto;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserApp getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserApp getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserApp> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public UserApp createAdmin(@RequestBody UserApp userApp) throws Exception {
        return userService.createAdmin(userApp);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllUsersWithEmail(@PathVariable String email) {
        userService.deleteAllUsersWithEmail(email);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserApp updateUser(@PathVariable Long id, UpdateUserDto updateUserDto) {
        return userService.updateUser(id, updateUserDto);
    }
}
