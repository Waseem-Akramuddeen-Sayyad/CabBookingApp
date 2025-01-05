package com.CabBookingAppApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.CabBokkingAppApplication.Logins","com.CabBookingAppApplication.Controllers", "com.CabBookingAppApplication.Services", "com.CabBookingAppApplication.Repositries", "com.CabBookingAppApplication.SecurityConfig", "com.CabBookingAppApplication.JWT", "com.CabBookingAppApplication.Models"})
@EnableMongoRepositories(basePackages = "com.CabBookingAppApplication.Repositories")
public class CabBookingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CabBookingAppApplication.class, args);
    }

}

