package com.example.HotelKingBackend.auth;

import com.example.HotelKingBackend.config.JwtService;
import com.example.HotelKingBackend.models.Employee;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterUserRequest request) {
        UserApp userApp = new UserApp();
        userApp.setEmail(request.getEmail());
        userApp.setPassword(passwordEncoder.encode(request.getPassword()));
        userApp.setFirstname(request.getFirstname());
        userApp.setLastname(request.getLastname());
        userApp.setPhoneNumber(request.getPhoneNumber());
        userApp.setRole(Role.USER);
        userRepository.save(userApp);
        var jwtToken = jwtService.generateToken(userApp);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserApp userApp = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + request.getEmail() + " not found"));
        var jwtToken = jwtService.generateToken(userApp);
        return new AuthenticationResponse(jwtToken);
    }
}
