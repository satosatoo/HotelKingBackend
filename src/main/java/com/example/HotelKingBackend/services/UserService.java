package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.dto.UpdateGuestDto;
import com.example.HotelKingBackend.models.UserApp;
import com.example.HotelKingBackend.models.Role;
import com.example.HotelKingBackend.repositories.GuestRepository;
import com.example.HotelKingBackend.repositories.JobPositionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return guestRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Guest with email " + username + " not found"));
    }

    public UserApp getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guest with id " + id + " not found"));
    }

    public UserApp getGuestByEmail(String email) {
        return guestRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Guest with email " + email + " not found"));
    }

    public List<UserApp> getAllGuests() {
        return guestRepository.findAll();
    }

    public UserApp createGuest(UserApp userApp) {
        userApp.setRole(Role.GUEST);
        return guestRepository.save(userApp);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public void deleteAllGuestWithEmail(String email) {
        guestRepository.deleteGuestsByEmail(email);
    }

    public UserApp updateGuest(Long guestId, UpdateGuestDto updateGuestDto) {
        UserApp existingUserApp = guestRepository.findById(guestId).orElse(null);

        if (existingUserApp != null) {
            if (updateGuestDto.getPassword() != null) {
                existingUserApp.setPassword(updateGuestDto.getPassword());
            }
            if (updateGuestDto.getPhoneNumber() != null) {
                existingUserApp.setPhoneNumber(updateGuestDto.getPhoneNumber());
            }
        } else {
            throw new EntityNotFoundException("Guest with id " + guestId + " not found");
        }

        return guestRepository.save(existingUserApp);
    }


}
