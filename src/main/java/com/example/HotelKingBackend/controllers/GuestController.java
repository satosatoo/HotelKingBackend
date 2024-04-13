package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.dto.UpdateGuestDto;
import com.example.HotelKingBackend.models.Guest;
import com.example.HotelKingBackend.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/id/{id}")
    public Guest getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @GetMapping("/email/{email}")
    public Guest getGuestByEmail(@PathVariable String email) {
        return guestService.getGuestByEmail(email);
    }

    @GetMapping("/")
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @PostMapping("/")
    public Guest createGuest(Guest guest) {
        return guestService.createGuest(guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }

    @DeleteMapping("/{email}")
    public void deleteAllGuestsWithEmail(@PathVariable String email) {
        guestService.deleteAllGuestWithEmail(email);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, UpdateGuestDto updateGuestDto) {
        return guestService.updateGuest(id, updateGuestDto);
    }
}
