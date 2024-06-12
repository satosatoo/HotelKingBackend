package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.dto.UpdateUserDto;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public UserApp getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public UserApp getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public List<UserApp> getAllUsers() {
        return userRepository.findAll();
    }

    public UserApp createUser(UserApp userApp) throws Exception {

        if (userApp.getPhoneNumber() == null || userApp.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        String phoneNumberPattern = "^[0-9]+$";
        if (!userApp.getPhoneNumber().matches(phoneNumberPattern) || userApp.getPhoneNumber().length() + 1 <= 10 || userApp.getPhoneNumber().length() + 1 >= 15) {
            throw new IllegalArgumentException("Invalid phone number format");
        }

        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        userApp.setRole(Role.USER);
        return userRepository.save(userApp);
    }

    public UserApp createAdmin(UserApp userApp) throws Exception {

        if (userApp.getPhoneNumber() == null || userApp.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        String phoneNumberPattern = "^[0-9]+$";
        if (!userApp.getPhoneNumber().matches(phoneNumberPattern) || userApp.getPhoneNumber().length() + 1 <= 10 || userApp.getPhoneNumber().length() + 1 >= 15) {
            throw new IllegalArgumentException("Invalid phone number format");
        }

        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        userApp.setRole(Role.ADMIN);
        return userRepository.save(userApp);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsersWithEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    public UserApp updateUser(String email, UpdateUserDto updateUserDto) {
        UserApp existingUser = userRepository.findByEmail(email).orElse(null);

        if (existingUser != null) {
            if (updateUserDto.getPassword() != null && !updateUserDto.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
            }
            if (updateUserDto.getFirstname() != null) {
                existingUser.setFirstname(updateUserDto.getFirstname());
            }
            if (updateUserDto.getLastname() != null) {
                existingUser.setLastname(updateUserDto.getLastname());
            }
            if (updateUserDto.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(updateUserDto.getPhoneNumber());
            }
        } else {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }

        return userRepository.save(existingUser);
    }
}
