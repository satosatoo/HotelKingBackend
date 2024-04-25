package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.models.Room;
import com.example.HotelKingBackend.models.RoomFacility;
import com.example.HotelKingBackend.models.RoomReview;
import com.example.HotelKingBackend.services.RoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoom(id);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available/")
    @ResponseBody
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Room> getAvailableRooms(
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate
    ) {
        return roomService.getAvailableRooms(checkInDate, checkOutDate);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Room createRoom(@RequestBody Room room) {
        List<RoomFacility> existingFacilities = new ArrayList<>();
        for (RoomFacility facility : room.getFacilities()) {
            existingFacilities.add(roomService.getRoomFacility(facility.getFacilityId()));
        }
        room.setFacilities(existingFacilities);
        return roomService.createRoom(room);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Room updateRoom(
            @PathVariable int id,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "roomPrice", required = false) Double roomPrice
    ) {
        if (roomService.getRoom(id) == null) {
            throw new EntityNotFoundException("Room with id " + id + " not found");
        }

        if (description != null && !description.isEmpty() && roomPrice != 0) {
            roomService.updateRoomDesciption(id, description);
            return roomService.updateRoomPrice(id, roomPrice);
        } else if (description != null && !description.isEmpty()) {
            return roomService.updateRoomDesciption(id, description);
        } else if (roomPrice != 0) {
            return roomService.updateRoomPrice(id, roomPrice);
        } else {
            throw new RuntimeException("Not all required parameters were provided.");
        }
    }


    // RoomFacility
    @GetMapping("/facility/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomFacility getRoomFacilityByName(@PathVariable String name) {
        return roomService.getRoomFacilityByName(name);
    }

    @GetMapping("/facility/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public RoomFacility getRoomFacilityByName(@PathVariable int id) {
        return roomService.getRoomFacility(id);
    }

    @GetMapping("/facility/")
    @PreAuthorize("hasRole('ADMIN')")
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


    // RoomReview
    @GetMapping("/review/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public RoomReview getRoomReview(@PathVariable Long id) {
        return roomService.getRoomReview(id);
    }

    @GetMapping("/review/guest/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<RoomReview> getAllRoomReviewsFromGuest(@PathVariable Long id) {
        return roomService.getAllRoomReviewsFromUser(id);
    }

    @GetMapping("/review/room/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<RoomReview> getAllRoomReviewsForRoom(@PathVariable int id) {
        return roomService.getAllRoomReviewsForRoom(id);
    }

    @PostMapping("/{id}/review/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public RoomReview createRoomReview(@RequestBody RoomReview roomReview, @PathVariable Integer id) throws AccessDeniedException {
        return roomService.createRoomReview(roomReview, id);
    }

    @DeleteMapping("/review/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRoomReview(@PathVariable Long id) {
        roomService.deleteRoomReview(id);
    }
}
