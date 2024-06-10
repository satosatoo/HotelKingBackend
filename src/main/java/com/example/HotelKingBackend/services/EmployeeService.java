//package com.example.HotelKingBackend.services;
//
//import com.example.HotelKingBackend.dto.UpdateEmployeeDto;
//import com.example.HotelKingBackend.models.Employee;
//import com.example.HotelKingBackend.models.JobPosition;
//import com.example.HotelKingBackend.repositories.EmployeeRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private JobPositionRepository jobPositionRepository;
//
//    public Employee getEmployeeById(Long id) {
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found"));
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public Employee createEmployee(Employee employee) {
//        Integer jobPosId = employee.getJob_position().getPositionId();
//        JobPosition jobPosition = jobPositionRepository.findById(jobPosId).orElseThrow(() -> new EntityNotFoundException("Job position with " + jobPosId + " not found"));
//        employee.setJob_position(jobPosition);
//
//        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty()) {
//            throw new IllegalArgumentException("Phone number is required");
//        }
//        String phoneNumberPattern = "^[0-9]+$";
//        if (!employee.getPhoneNumber().matches(phoneNumberPattern) || employee.getPhoneNumber().length() + 1 <= 10 || employee.getPhoneNumber().length() + 1 >= 15) {
//            throw new IllegalArgumentException("Invalid phone number format");
//        }
//
//        return employeeRepository.save(employee);
//    }
//
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//
//    public Employee updateEmployee(Long employeeId, UpdateEmployeeDto updatedEmployee) {
//        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);
//
//        if (existingEmployee != null) {
//            if (updatedEmployee.getPhoneNumber() != null) {
//                existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
//            }
//            if (updatedEmployee.getWorkingDays() != null) {
//                existingEmployee.setWorkingDays(updatedEmployee.getWorkingDays());
//            }
//            if (updatedEmployee.getWorkingTime() != null) {
//                existingEmployee.setWorkingTime(updatedEmployee.getWorkingTime());
//            }
//            if (updatedEmployee.getJobPositionId() != null && updatedEmployee.getJobPositionId() != 0) {
//                existingEmployee.setJob_position(jobPositionRepository.findById(updatedEmployee.getJobPositionId())
//                        .orElseThrow(() -> new EntityNotFoundException("Job position with id " + updatedEmployee.getJobPositionId() + " not found")));
//            }
//        } else {
//            throw new EntityNotFoundException("Employee with id " + employeeId + " not found");
//        }
//
//        return employeeRepository.save(existingEmployee);
//    }
//
//    public JobPosition getJobPosition(int id) {
//        return jobPositionRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Job position with " + id + " not found"));
//    }
//
//    public List<JobPosition> getAllJobPositions() {
//        return jobPositionRepository.findAll();
//    }
//
//    public JobPosition createJobPosition(JobPosition jobPosition) {
//        return jobPositionRepository.save(jobPosition);
//    }
//}
