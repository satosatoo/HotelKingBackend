package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Integer> {
    List<Extra> findAllByExtraIdIn(List<Integer> ids);
}
