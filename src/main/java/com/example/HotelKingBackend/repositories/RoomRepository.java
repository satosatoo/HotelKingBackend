package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE NOT EXISTS " +
            "(SELECT 1 FROM r.reservations res WHERE :checkOutDate > res.checkInDate AND :checkInDate < res.checkOutDate)")
    List<Room> findAvailableRoomsBetweenDates(LocalDate checkIn, LocalDate checkOut);
}
