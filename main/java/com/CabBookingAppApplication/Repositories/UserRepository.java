package com.CabBookingAppApplication.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBookingAppApplication.Models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}