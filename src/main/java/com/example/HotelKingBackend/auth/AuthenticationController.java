package com.example.HotelKingBackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register-guest")
    public ResponseEntity<AuthenticationResponse> registerGuest(@RequestBody RegisterGuestRequest request) {
        return ResponseEntity.ok(authenticationService.registerGuest(request));
    }

    @PostMapping("/register-employee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(@RequestBody RegisterEmployeeRequest request) {
        return ResponseEntity.ok(authenticationService.registerEmployee(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
