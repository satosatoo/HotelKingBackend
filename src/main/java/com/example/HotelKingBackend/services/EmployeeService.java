//package com.example.HotelKingBackend.services;
//
//import com.example.HotelKingBackend.models.Employee;
//import com.example.HotelKingBackend.repositories.EmployeeRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class EmployeeService implements UserDetailsService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private static EmployeeRepository staticEmployeeRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return employeeRepository.findByEmail(email)
//                .orElseThrow(() -> new EntityNotFoundException("Employee with email " + email + " not found"));
//    }
//
//    public static Long getNextEmployeeId() {
//        Employee employee = staticEmployeeRepository.findFirstByOrderByEmployeeIdDesc().orElse(null);
//        if (employee != null)
//            return employee.getUserId() + 1;
//        else
//            return 1L;
//    }
//}
