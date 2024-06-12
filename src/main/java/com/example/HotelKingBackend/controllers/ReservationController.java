package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.models.Extra;
import com.example.HotelKingBackend.models.Reservation;
import com.example.HotelKingBackend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Reservation> getAllReservationsByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return reservationService.getAllReservationsByUser(auth);
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Reservation createReservation(@RequestBody Reservation reservation, @PathVariable int id) {
        return reservationService.createReservation(reservation, id);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
