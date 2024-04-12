package com.example.HotelKingBackend.dto;

import com.example.HotelKingBackend.models.Employee;

public class LoginResponseEmployee extends LoginResponse {

    private Employee employee;

    public LoginResponseEmployee(String jwt, Employee employee) {
        super(jwt);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployeeDto(Employee employee) {
        this.employee = employee;
    }
}
