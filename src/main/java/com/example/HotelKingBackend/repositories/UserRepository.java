package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
    List<UserApp> deleteUserByEmail(String email);
    Optional<UserApp> findFirstByOrderByUserIdDesc();
}
