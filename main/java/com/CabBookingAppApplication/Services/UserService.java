package com.CabBookingAppApplication.Services;

import org.springframework.stereotype.Service;

import com.CabBookingAppApplication.Models.User;
import com.CabBookingAppApplication.Repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void updateUser(String userId, User user) {
        user.setUserId(userId);
        userRepository.save(user);
    }
}
