package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Room;
import com.example.HotelKingBackend.models.RoomFacility;
import com.example.HotelKingBackend.repositories.UserRepository;
import com.example.HotelKingBackend.repositories.RoomFacilityRepository;
import com.example.HotelKingBackend.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleStatus;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public Room createRoom(Room room) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserApp user = userRepository.findByEmail(auth.getName()).orElse(null);
        List<RoomFacility> existingFacilities = new ArrayList<>();
        for (RoomFacility facility : room.getFacilities()) {
            existingFacilities.add(getRoomFacility(facility.getFacilityId()));
        }
        room.setFacilities(existingFacilities);
        return roomRepository.save(room);
    }

    public void deleteRoom(int id) { roomRepository.deleteById(id); }

    public Room updateRoom(int roomId, Room updatedRoom) {
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));

        if (updatedRoom.getName() != null && !updatedRoom.getName().isEmpty()) {
            existingRoom.setName(updatedRoom.getName());
        }
        if (updatedRoom.getNumberOfGuests() != null) {
            existingRoom.setNumberOfGuests(updatedRoom.getNumberOfGuests());
        }
        if (updatedRoom.getDescription() != null && !updatedRoom.getDescription().isEmpty()) {
            existingRoom.setDescription(updatedRoom.getDescription());
        }
        if (updatedRoom.getRoomSize() != null) {
            existingRoom.setRoomSize(updatedRoom.getRoomSize());
        }
        if (updatedRoom.getBedSize() != null && !updatedRoom.getBedSize().isEmpty()) {
            existingRoom.setBedSize(updatedRoom.getBedSize());
        }
        if (updatedRoom.getRoomType() != null) {
            existingRoom.setRoomType(updatedRoom.getRoomType());
        }
        if (updatedRoom.getCostPerNight() != 0) {
            existingRoom.setCostPerNight(updatedRoom.getCostPerNight());
        }

        List<RoomFacility> existingFacilities = new ArrayList<>();
        for (RoomFacility facility : updatedRoom.getFacilities()) {
            existingFacilities.add(getRoomFacility(facility.getFacilityId()));
        }
        existingRoom.setFacilities(existingFacilities);

        return roomRepository.save(existingRoom);
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