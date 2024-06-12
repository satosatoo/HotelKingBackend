package com.example.HotelKingBackend.controllers;


import com.example.HotelKingBackend.models.Extra;
import com.example.HotelKingBackend.services.ExtraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extra")
public class ExtraController {

    private ExtraService extraService;

    public ExtraController(ExtraService extraService) {
        this.extraService = extraService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Extra getExtra(@PathVariable int id) {
        return extraService.getExtra(id);
    }

    @GetMapping("/")
//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Extra> getAllExtras() {
        return extraService.getAllExtras();
    }

    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public Extra createExtra(@RequestBody Extra extra) {
        return extraService.createExtra(extra);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteExtra(@PathVariable int id) {
        extraService.deleteExtra(id);
    }
}
