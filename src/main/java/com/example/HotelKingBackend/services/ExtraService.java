package com.example.HotelKingBackend.services;

import com.example.HotelKingBackend.models.Extra;
import com.example.HotelKingBackend.repositories.ExtraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExtraService {

    private ExtraRepository extraRepository;

    public ExtraService(ExtraRepository extraRepository) {
        this.extraRepository = extraRepository;
    }

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
