package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.auth.RegisterUserRequest;
import com.example.HotelKingBackend.dto.UpdateUserDto;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public UserApp getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    //@PreAuthorize("hasRole('ADMIN')")
    public UserApp getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/profile")
    //@PreAuthorize("hasRole('ADMIN')")
    public UserApp getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.getUserByEmail(email);
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<UserApp> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create-admin")
    //@PreAuthorize("hasRole('ADMIN')")
    public UserApp createAdmin(@RequestBody UserApp userApp) throws Exception {
        return userService.createAdmin(userApp);
    }

    @PostMapping("/create-user")
    //@PreAuthorize("hasRole('ADMIN')")
    public UserApp createUser(@RequestBody UserApp userApp) throws Exception {
        return userService.createUser(userApp);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/{email}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteAllUsersWithEmail(@PathVariable String email) {
        userService.deleteAllUsersWithEmail(email);
    }

    @PutMapping
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserApp updateUser( @RequestBody UpdateUserDto updateUserDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.updateUser(email, updateUserDto);
    }
}
