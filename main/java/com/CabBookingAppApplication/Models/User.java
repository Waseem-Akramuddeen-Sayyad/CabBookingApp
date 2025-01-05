package com.CabBookingAppApplication.Models;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
@Data
@Document(collection = "Users")
public class User {
    @Id // primary key 
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String rating;
    private LocalDateTime createdAt;
}
