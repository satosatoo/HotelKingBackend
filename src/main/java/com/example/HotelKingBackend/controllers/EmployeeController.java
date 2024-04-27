package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.dto.UpdateEmployeeDto;
import com.example.HotelKingBackend.models.Employee;
import com.example.HotelKingBackend.models.JobPosition;
import com.example.HotelKingBackend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }
    
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.updateEmployee(id, updateEmployeeDto);
    }


    @GetMapping("/job-position/{id}")
    public JobPosition getJobPosition(@PathVariable Integer id) {
        return employeeService.getJobPosition(id);
    }

    @GetMapping("/job-position/")
    public List<JobPosition> getAllJobPositions() {
        return employeeService.getAllJobPositions();
    }

    @PostMapping("/job-position/")
    public JobPosition createJobPosition(@RequestBody JobPosition jobPosition) {
        return employeeService.createJobPosition(jobPosition);
    }
}
