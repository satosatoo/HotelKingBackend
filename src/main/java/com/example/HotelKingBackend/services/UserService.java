package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.dto.UpdateUserDto;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + username + " not found"));
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
        userApp.setRole(Role.USER);
        return userRepository.save(userApp);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsersWithEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    public UserApp updateUser(Long guestId, UpdateUserDto updateUserDto) {
        UserApp existingUserApp = userRepository.findById(guestId).orElse(null);

        if (existingUserApp != null) {
            if (updateUserDto.getPassword() != null) {
                existingUserApp.setPassword(updateUserDto.getPassword());
            }
            if (updateUserDto.getPhoneNumber() != null) {
                existingUserApp.setPhoneNumber(updateUserDto.getPhoneNumber());
            }
        } else {
            throw new EntityNotFoundException("User with id " + guestId + " not found");
        }

        return userRepository.save(existingUserApp);
    }
}
