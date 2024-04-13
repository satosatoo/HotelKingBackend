package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.dto.UpdateGuestDto;
import com.example.HotelKingBackend.models.Guest;
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
public class GuestService implements UserDetailsService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return guestRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Guest with email " + username + " not found"));
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guest with id " + id + " not found"));
    }

    public Guest getGuestByEmail(String email) {
        return guestRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Guest with email " + email + " not found"));
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest createGuest(Guest guest) {
        guest.setRole(Role.USER);
        return guestRepository.save(guest);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public void deleteAllGuestWithEmail(String email) {
        guestRepository.deleteGuestsByEmail(email);
    }

    public Guest updateGuest(Long guestId, UpdateGuestDto updateGuestDto) {
        Guest existingGuest = guestRepository.findById(guestId).orElse(null);

        if (existingGuest != null) {
            if (updateGuestDto.getPassword() != null) {
                existingGuest.setPassword(updateGuestDto.getPassword());
            }
            if (updateGuestDto.getPhoneNumber() != null) {
                existingGuest.setPhoneNumber(updateGuestDto.getPhoneNumber());
            }
        } else {
            throw new EntityNotFoundException("Guest with id " + guestId + " not found");
        }

        return guestRepository.save(existingGuest);
    }
}
