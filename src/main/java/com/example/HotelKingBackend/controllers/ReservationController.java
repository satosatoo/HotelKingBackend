package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.models.Extra;
import com.example.HotelKingBackend.models.Reservation;
import com.example.HotelKingBackend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    // Reservation
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/guest/{id}")
    public List<Reservation> getAllReservationsByGuestId(@PathVariable Long id) {
        return reservationService.getAllReservationsByUserId(id);
    }

    @GetMapping("/")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/{id}")
    public Reservation createReservation(@RequestBody Reservation reservation, @PathVariable int id) {
        return reservationService.createReservation(reservation, id);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }


    // Extra
    @GetMapping("/extra/{id}")
    public Extra getExtra(@PathVariable int id) {
        return reservationService.getExtra(id);
    }

    @GetMapping("/extra/")
    public List<Extra> getAllExtras() {
        return reservationService.getAllExtras();
    }

    @PostMapping("/extra/")
    public Extra createExtra(@RequestBody Extra extra) {
        return reservationService.createExtra(extra);
    }

    @DeleteMapping("/extra/{id}")
    public void deleteExtra(@PathVariable int id) {
        reservationService.deleteExtra(id);
    }

    @PutMapping("/extra/{id}")
    public Extra updateExtra(@PathVariable int id, @RequestParam Double cost) {
        return reservationService.updateExtra(id, cost);
    }
}
