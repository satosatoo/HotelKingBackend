package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.models.*;
import com.example.HotelKingBackend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ExtraRepository extraRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;


    // Reservation crud
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with id " + id + " not found"));
    }

    public List<Reservation> getAllReservationsByUserId(Long id) {
        UserApp userApp = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        return reservationRepository.findAllByUser(userApp);
    }

    public List<Reservation> getAllReservations() { return reservationRepository.findAll(); }

    public Reservation createReservation(Reservation reservation, int roomId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        reservation.setUser(userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User is not authenticated or with email " + email + "not found")));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));
        if (!roomRepository.findAvailableRoomById(roomId, reservation.getCheckInDate(), reservation.getCheckOutDate()).isPresent()) {
            throw new EntityNotFoundException("Room with id " + roomId + " is occupied on these dates");
        } else if (!reservation.getCheckOutDate().isAfter(reservation.getCheckInDate())
                || reservation.getCheckInDate().isEqual(reservation.getCheckOutDate())) {
            throw new EntityNotFoundException("The check in and check out dates must not be on the same day and the check out date must not be less than the check in date");
        }
        reservation.setRoom(room);

        long numberOfNights = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        double roomCost = numberOfNights * room.getCostPerNight();
        double tax = 0.1 * roomCost;
        double totalCost = roomCost + tax;
        reservation.setTotalCost(totalCost);
        
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }


    // Extra crud
    public Extra getExtra(int id) {
        return extraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Extra with id " + id + " not found"));
    }

    public List<Extra> getAllExtras() {
        return extraRepository.findAll();
    }

    public Extra createExtra(Extra extra) {
        return extraRepository.save(extra);
    }

    public void deleteExtra(int id) {
        extraRepository.deleteById(id);
    }
}
