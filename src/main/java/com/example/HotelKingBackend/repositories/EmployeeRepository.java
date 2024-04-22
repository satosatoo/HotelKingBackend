package com.example.HotelKingBackend.repositories;

import com.example.HotelKingBackend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findFirstByOrderByEmployeeIdDesc();
}
