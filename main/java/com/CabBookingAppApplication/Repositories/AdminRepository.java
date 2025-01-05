package com.CabBookingAppApplication.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBookingAppApplication.Models.Admin;
public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}

