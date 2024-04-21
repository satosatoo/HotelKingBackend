package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByGuest(UserApp userApp);
}
