package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
    List<UserApp> deleteGuestsByEmail(String email);
    Optional<UserApp> findFirstByOrderByGuestIdDesc();
}
