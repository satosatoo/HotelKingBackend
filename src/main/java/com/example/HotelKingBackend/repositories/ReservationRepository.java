package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.Guest;
import com.example.HotelKingBackend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByGuest(Guest guest);
}
