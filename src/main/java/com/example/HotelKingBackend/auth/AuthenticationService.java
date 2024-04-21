package com.example.HotelKingBackend.auth;

import com.example.HotelKingBackend.config.JwtService;
import com.example.HotelKingBackend.models.Employee;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.repositories.EmployeeRepository;
import com.example.HotelKingBackend.repositories.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final GuestRepository guestRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerGuest(RegisterGuestRequest request) {
        UserApp userApp = new UserApp();
        userApp.setEmail(request.getEmail());
        userApp.setPassword(passwordEncoder.encode(request.getPassword()));
        userApp.setFirstname(request.getFirstname());
        userApp.setLastname(request.getLastname());
        userApp.setPhoneNumber(request.getPhoneNumber());
        userApp.setRole(Role.GUEST);
        guestRepository.save(userApp);
        var jwtToken = jwtService.generateToken(userApp);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse registerEmployee(RegisterEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setWorkingDays(request.getWorkingDays());
        employee.setWorkingTime(request.getWorkingHours());
        employee.setDob(request.getDob());
        employee.setPlaceOfResidence(request.getPlaceOfResidence());
        employee.setGender(request.getGender());
        employee.setJob_position(request.getJobPosition());
        employee.setRole(Role.EMPLOYEE);
        employeeRepository.save(employee);
        var jwtToken = jwtService.generateToken(employee);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserApp userApp = guestRepository.findByEmail(request.getEmail()).orElse(null);
        Employee employee = employeeRepository.findByEmail(request.getEmail()).orElse(null);
        if (userApp != null) {
            return generateGuestAuthenticationResponse(userApp);
        } else if (employee != null) {
            return generateEmployeeAuthenticationResponse(employee);
        } else {
            return null;
        }
    }

    private AuthenticationResponse generateGuestAuthenticationResponse(UserApp userApp) {
        var jwtToken = jwtService.generateToken(userApp);
        return new AuthenticationResponse(jwtToken);
    }

    private AuthenticationResponse generateEmployeeAuthenticationResponse(Employee employee) {
        var jwtToken = jwtService.generateToken(employee);
        return new AuthenticationResponse(jwtToken);
    }
}
