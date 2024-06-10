package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Room;
import com.example.HotelKingBackend.models.RoomFacility;
import com.example.HotelKingBackend.repositories.UserRepository;
import com.example.HotelKingBackend.repositories.RoomFacilityRepository;
import com.example.HotelKingBackend.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomFacilityRepository roomFacilityRepository;

    @Autowired
    private UserRepository userRepository;

    // Room crud
    public Room getRoom(int id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room with id " + id + "not found"));
    }

    public List<Room> getAllRooms() { return roomRepository.findAll(); }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int people) {
        return roomRepository.findAvailableRoomsBetweenDates(checkIn, checkOut, people);
    }

    public Room createRoom(Room room) { return roomRepository.save(room); }

    public void deleteRoom(int id) { roomRepository.deleteById(id); }

    public Room updateRoomDesciption(int roomId, String description) {
        Room updatedRoom = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));
        updatedRoom.setDescription(description);
        return roomRepository.save(updatedRoom);
    }

    public Room updateRoomPrice(int roomId, double cost) {
        Room updatedRoom = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));
        updatedRoom.setCostPerNight(cost);
        return roomRepository.save(updatedRoom);
    }


    // RoomFacility crud
    public RoomFacility getRoomFacilityByName(String name) {
        return roomFacilityRepository.findRoomFacilityByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Room facility with name " + name + " not found"));
    }

    public RoomFacility getRoomFacility(int id) {
        return roomFacilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room facility with id " + id + " not found"));
    }

    public List<RoomFacility> getAllRoomFacilities() {
        return roomFacilityRepository.findAll();
    }

    public RoomFacility createRoomFacility(RoomFacility roomFacility) {
        return roomFacilityRepository.save(roomFacility);
    }

    public void deleteRoomFacility(int id) {
        roomFacilityRepository.deleteById(id);
    }
}