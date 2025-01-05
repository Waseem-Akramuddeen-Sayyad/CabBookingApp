package com.CabBookingAppApplication.Models;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
@Data
@Document(collection = "Admin")
public class Admin {
    @Id
    private String adminId;
    private String name;
    private String email;
    private String password;
    private String role;
    private List<String> permissions;
}

