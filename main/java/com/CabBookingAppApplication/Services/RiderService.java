package com.CabBookingAppApplication.Services;
import com.CabBookingAppApplication.Models.Rider;
import com.CabBookingAppApplication.Repositories.RiderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RiderService implements UserDetailsService {

    private final RiderRepository riderRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RiderService(RiderRepository riderRepository, PasswordEncoder passwordEncoder) {
        this.riderRepository = riderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new rider with an encoded password.
     *
     * @param rider Rider object to be registered.
     * @return The registered Rider with the encoded password.
     */
    public Rider registerRider(Rider rider) {
        rider.setPassword(passwordEncoder.encode(rider.getPassword()));
        return riderRepository.save(rider);
    }

    /**
     * Loads a rider by email for authentication purposes.
     *
     * @param email The email of the rider.
     * @return UserDetails object containing the rider's authentication details.
     * @throws UsernameNotFoundException if the rider is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Rider rider = riderRepository.findByEmail(email);
        if (rider == null) {
            throw new UsernameNotFoundException("Rider not found");
        }
        return User.withUsername(rider.getEmail())
                .password(rider.getPassword())
                .authorities(Collections.emptyList()) // Add roles or authorities if needed
                .build();
    }
}

