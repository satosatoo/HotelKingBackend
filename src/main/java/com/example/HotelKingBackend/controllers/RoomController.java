package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.models.Room;
import com.example.HotelKingBackend.models.RoomFacility;
import com.example.HotelKingBackend.services.RoomService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    // Room
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoom(id);
    }

    @GetMapping("/")
    // @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam("people") int people
    ) {
        return roomService.getAvailableRooms(checkInDate, checkOutDate, people);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN)")
    public Room createRoom(@RequestBody Room room) throws Exception {
        return roomService.createRoom(room);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Room updateRoom(@PathVariable int id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }


    // RoomFacility
    @GetMapping("/facility/name/{name}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public RoomFacility getRoomFacilityByName(@PathVariable String name) {
        return roomService.getRoomFacilityByName(name);
    }

    @GetMapping("/facility/id/{id}")
     @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public RoomFacility getRoomFacilityByName(@PathVariable int id) {
        return roomService.getRoomFacility(id);
    }

    @GetMapping("/facility/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<RoomFacility> getAllRoomFacilities() {
        return roomService.getAllRoomFacilities();
    }

    @PostMapping("/facility/")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomFacility createRoomFacility(@RequestBody RoomFacility roomFacility) {
        return roomService.createRoomFacility(roomFacility);
    }

    @DeleteMapping("/facility/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRoomFacility(@PathVariable int id) {
        roomService.deleteRoomFacility(id);
    }
}
