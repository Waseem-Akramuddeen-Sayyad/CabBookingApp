package com.CabBookingAppApplication.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.CabBookingAppApplication.Models.Rider;

public interface RiderRepository extends MongoRepository<Rider, String> {
    Rider findByEmail(String email);
}

