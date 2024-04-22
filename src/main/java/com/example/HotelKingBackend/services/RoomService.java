package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Room;
import com.example.HotelKingBackend.models.RoomFacility;
import com.example.HotelKingBackend.models.RoomReview;
import com.example.HotelKingBackend.repositories.UserRepository;
import com.example.HotelKingBackend.repositories.RoomFacilityRepository;
import com.example.HotelKingBackend.repositories.RoomRepository;
import com.example.HotelKingBackend.repositories.RoomReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private RoomReviewRepository roomReviewRepository;

    @Autowired
    private UserRepository userRepository;

    // Room crud
    public Room getRoom(int id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room with id " + id + "not found"));
    }

    public List<Room> getAllRooms() { return roomRepository.findAll(); }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        return roomRepository.findAvailableRoomsBetweenDates(checkIn, checkOut);
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


    // RoomReview crud
    public RoomReview getRoomReview(Long id) {
        return roomReviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room review with id " + id + " not found"));
    }

    public List<RoomReview> getAllRoomReviewsFromUser(Long id) {
        UserApp userApp = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        return roomReviewRepository.findByUser(userApp);
    }

    public List<RoomReview> getAllRoomReviewsForRoom(int id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + id + " not found"));
        return roomReviewRepository.findByRoom(room);
    }

    public RoomReview createRoomReview(RoomReview roomReview, Integer roomId) throws AccessDeniedException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserApp userApp = userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + username + " not found"));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));
        roomReview.setRoom(room);
        roomReview.setUser(userApp);
        return roomReviewRepository.save(roomReview);
    }

    public void deleteRoomReview(Long id) {
        roomReviewRepository.deleteById(id);
    }
}