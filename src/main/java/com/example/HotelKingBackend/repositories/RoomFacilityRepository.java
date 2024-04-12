package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Integer> {
    Optional<RoomFacility> findRoomFacilityByName(String name);
}
