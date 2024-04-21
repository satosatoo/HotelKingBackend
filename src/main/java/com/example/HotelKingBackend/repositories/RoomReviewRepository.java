package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Room;
import com.example.HotelKingBackend.models.RoomReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomReviewRepository extends JpaRepository<RoomReview, Long> {
    List<RoomReview> findByRoom(Room room);
    List<RoomReview> findByGuest(UserApp userApp);
}
