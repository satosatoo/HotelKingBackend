package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.dto.UpdateGuestDto;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    public UserApp getGuestById(@PathVariable Long id) {
        return userService.getGuestById(id);
    }

    @GetMapping("/email/{email}")
    public UserApp getGuestByEmail(@PathVariable String email) {
        return userService.getGuestByEmail(email);
    }

    @GetMapping("/")
    public List<UserApp> getAllGuests() {
        return userService.getAllGuests();
    }

    @PostMapping("/")
    public UserApp createGuest(UserApp userApp) {
        return userService.createGuest(userApp);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        userService.deleteGuest(id);
    }

    @DeleteMapping("/{email}")
    public void deleteAllGuestsWithEmail(@PathVariable String email) {
        userService.deleteAllGuestWithEmail(email);
    }

    @PutMapping("/{id}")
    public UserApp updateGuest(@PathVariable Long id, UpdateGuestDto updateGuestDto) {
        return userService.updateGuest(id, updateGuestDto);
    }
}
