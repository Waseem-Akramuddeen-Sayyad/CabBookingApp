package com.CabBookingAppApplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.CabBookingAppApplication.Logins.RiderLogin;
import com.CabBookingAppApplication.Models.Rider;
import com.CabBookingAppApplication.Repositories.RiderRepository;
@RestController
@RequestMapping("/rider")
public class RiderController {
    private final RiderRepository riderRepository;
     @Autowired
     public RiderController(RiderRepository riderRepository){
           this.riderRepository = riderRepository;
     }

    @PostMapping("/register")
    public Rider registerRider(@RequestBody Rider rider) {
        return riderRepository.save(rider);
    }
     @PostMapping("/login")
     public String RiderLogin(@RequestBody RiderLogin riderLogin ) {
        String password = riderLogin.getPassword();
        Rider rider = riderRepository.findByEmail(riderLogin.getEmail());
        if (rider == null || !rider.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return "user logged in successfully";
    }

    @PutMapping("/profile/{riderId}")
    public Rider updateRiderProfile(@PathVariable String riderId, @RequestBody Rider updatedRider) {
        Rider rider = riderRepository.findById(riderId).orElseThrow(() -> new RuntimeException("Rider not found"));
        rider.setName(updatedRider.getName());
        return riderRepository.save(rider);
    }
        @GetMapping("/{riderId}")
    public Rider getUserById(@PathVariable String riderId) {
        return riderRepository.findById(riderId).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "rider not found"));
    }
}

