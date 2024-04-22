package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.dto.UpdateUserDto;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    public UserApp getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public UserApp getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/")
    public List<UserApp> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public UserApp createUser(UserApp userApp) throws Exception {
        return userService.createUser(userApp);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/{email}")
    public void deleteAllUsersWithEmail(@PathVariable String email) {
        userService.deleteAllUsersWithEmail(email);
    }

    @PutMapping("/{id}")
    public UserApp updateUser(@PathVariable Long id, UpdateUserDto updateUserDto) {
        return userService.updateUser(id, updateUserDto);
    }
}
