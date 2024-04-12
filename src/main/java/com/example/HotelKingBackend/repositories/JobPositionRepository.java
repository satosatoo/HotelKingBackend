package com.example.HotelKingBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends JpaRepository<com.example.HotelKingBackend.models.JobPosition, Integer> {
}
