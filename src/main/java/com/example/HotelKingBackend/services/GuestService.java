//package com.example.HotelKingBackend.services;
//
//import com.example.HotelKingBackend.models.Employee;
//import com.example.HotelKingBackend.models.Guest;
//import com.example.HotelKingBackend.repositories.GuestRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class GuestService implements UserDetailsService {
//
//    @Autowired
//    private static GuestRepository staticGuestRepository;
//
//    @Autowired
//    private GuestRepository guestRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return guestRepository.findByEmail(email)
//                .orElseThrow(() -> new EntityNotFoundException("Guest with email " + email + " not found"));
//    }
//
//
//
////    public static GuestDto mapGuestToGuestDTO(Guest guest) {
////        if (guest != null) {
////            GuestDto guestDto = new GuestDto();
////            guestDto.setGuestId(guest.getGuestId());
////            guestDto.setEmail(guest.getEmail());
////            guestDto.setAuthorities(guest.getAuthorities());
////            guestDto.setName(guest.getName());
////            guestDto.setSurname(guest.getSurname());
////            guestDto.setPassword(guest.getPassword());
////            guestDto.setPhoneNumber(guest.getPhoneNumber());
////            guestDto.setRegistrationDate(guest.getRegistrationDate());
////            return guestDto;
////        } else {
////            throw new EntityNotFoundException("Guest was not found");
////        }
////    }
//}
