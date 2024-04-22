package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.dto.UpdateEmployeeDto;
import com.example.HotelKingBackend.models.Employee;
import com.example.HotelKingBackend.repositories.EmployeeRepository;
import com.example.HotelKingBackend.repositories.JobPositionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobPositionRepository jobPositionRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long employeeId, UpdateEmployeeDto updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (existingEmployee != null) {
            if (updatedEmployee.getPhoneNumber() != null) {
                existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            }
            if (updatedEmployee.getWorkingDays() != null) {
                existingEmployee.setWorkingDays(updatedEmployee.getWorkingDays());
            }
            if (updatedEmployee.getWorkingTime() != null) {
                existingEmployee.setWorkingTime(updatedEmployee.getWorkingTime());
            }
            if (updatedEmployee.getJobPositionId() != null && updatedEmployee.getJobPositionId() != 0) {
                existingEmployee.setJob_position(jobPositionRepository.findById(updatedEmployee.getJobPositionId())
                        .orElseThrow(() -> new EntityNotFoundException("Job position with id " + updatedEmployee.getJobPositionId() + " not found")));
            }
        } else {
            throw new EntityNotFoundException("Employee with id " + employeeId + " not found");
        }

        return employeeRepository.save(existingEmployee);
    }
}
