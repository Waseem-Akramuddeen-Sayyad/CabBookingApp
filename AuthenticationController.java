package com.CabBookingAppApplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CabBookingAppApplication.JWT.JWTUTIL;
import com.CabBookingAppApplication.Repositories.UserRepository;
import com.CabBookingAppApplication.Repositories.AdminRepository; // Import the Admin repository
import com.CabBookingAppApplication.Repositories.RiderRepository; // Import the Rider repository
import com.CabBookingAppApplication.Models.Admin; // Import the Admin model
import com.CabBookingAppApplication.Models.Rider; // Import the Rider model
import com.CabBookingAppApplication.Models.User; // Import the User model
import com.CabBookingAppApplication.Logins.UserLogin; // Assuming you have a UserLogin class for login requests
import com.CabBookingAppApplication.Logins.AdminLogin; // Assuming you have an AdminLoginRequest class
import com.CabBookingAppApplication.Logins.RiderLogin; // Assuming you have a RiderLoginRequest class

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JWTUTIL jwtUtil;
    private final UserRepository userRepository; 
    private final AdminRepository adminRepository; // For admin login
    private final RiderRepository riderRepository; // For rider login

    @Autowired
    public AuthenticationController(JWTUTIL jwtUtil, 
                                    UserRepository userRepository,
                                    AdminRepository adminRepository,
                                    RiderRepository riderRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository; // Injecting AdminRepository
        this.riderRepository = riderRepository; // Injecting RiderRepository
    }

    @PostMapping("/login/user")
    public String userLogin(@RequestBody UserLogin userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail());
        if (user == null || !user.getPassword().equals(userLogin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

    @PostMapping("/login/admin")
    public String adminLogin(@RequestBody AdminLogin adminLogin) {
        Admin admin = adminRepository.findByEmail(adminLogin.getEmail());
        if (admin == null || !admin.getPassword().equals(adminLogin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(admin.getEmail());
    }

    @PostMapping("/login/rider")
    public String riderLogin(@RequestBody RiderLogin riderLoginRequest) {
        Rider rider = riderRepository.findByEmail(riderLoginRequest.getEmail());
        if (rider == null || !rider.getPassword().equals(riderLoginRequest.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(rider.getEmail());
    }
}
