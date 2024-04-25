package com.example.HotelKingBackend.controllers;

import com.example.HotelKingBackend.dto.UpdateEmployeeDto;
import com.example.HotelKingBackend.models.Employee;
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
    public Employee createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(@PathVariable Long id, UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.updateEmployee(id, updateEmployeeDto);
    }
}
