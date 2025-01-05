package com.CabBookingAppApplication.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.CabBookingAppApplication.Logins.UserLogin;
import com.CabBookingAppApplication.Models.User;
import com.CabBookingAppApplication.Repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register new user
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/login")
     public String UserLogin(@RequestBody UserLogin userLogin ) {
        User user = userRepository.findByEmail(userLogin.getEmail());
        if (user == null || !user.getPassword().equals(userLogin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return "user logged in successfully";
    }
    
    // Update user profile by user ID
    @PutMapping("/profile/{userId}")
    public User updateUserProfile(@PathVariable String userId, @RequestBody User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setName(updatedUser.getName());
        user.setPhone(updatedUser.getPhone());
        return userRepository.save(user);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userRepository.findById(userId).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
