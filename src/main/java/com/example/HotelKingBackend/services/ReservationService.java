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

    @Autowired
    private PaymentRepository paymentRepository;


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
        Payment payment = reservation.getPayment();
        paymentRepository.save(payment);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));
        reservation.setRoom(room);

        List<Integer> extraIds = reservation.getExtras().stream().map(Extra::getExtraId).toList();
        List<Extra> extras = extraRepository.findAllByExtraIdIn(extraIds);
        reservation.setExtras(extras);

        long numberOfNights = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        double roomCost = numberOfNights * room.getCostPerNight();
        double extrasCost = reservation.getExtras().stream()
                .mapToDouble(Extra::getCost)
                .sum();
        double subtotal = roomCost + extrasCost;
        double tax = 0.1 * subtotal;
        double totalCost = subtotal + tax;
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

    public Extra updateExtra(int extraId, double cost) {
        Extra updatedExtra = extraRepository.findById(extraId)
                .orElseThrow(() -> new EntityNotFoundException("Extra with id " + extraId + " not found"));
        updatedExtra.setCost(cost);
        return extraRepository.save(updatedExtra);
    }
}
