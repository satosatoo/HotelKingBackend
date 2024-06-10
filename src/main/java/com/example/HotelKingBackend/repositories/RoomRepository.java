package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.numberOfGuests >= :people AND NOT EXISTS " +
            "(SELECT 1 FROM r.reservations res WHERE :checkOutDate > res.checkInDate AND :checkInDate < res.checkOutDate)")
    List<Room> findAvailableRoomsBetweenDates(
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("people") int people);

    @Query("SELECT r FROM Room r WHERE r.roomId = :roomId AND " +
            "r.roomId NOT IN (" +
            "SELECT res.room.roomId FROM Reservation res WHERE " +
            "(res.checkInDate < :checkOutDate AND res.checkOutDate > :checkInDate))")
    Optional<Room> findAvailableRoomById(@Param("roomId") int roomId,
                                         @Param("checkInDate") LocalDate checkInDate,
                                         @Param("checkOutDate") LocalDate checkOutDate);
}
