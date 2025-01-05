package com.CabBookingAppApplication.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.CabBookingAppApplication.Logins.AdminLogin;
import com.CabBookingAppApplication.Models.Admin;
import com.CabBookingAppApplication.Repositories.AdminRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    
     @PostMapping("/register")
     public Admin registerAdmin(@RequestBody Admin admin){
        return adminRepository.save(admin) ;
          
     }
     @PostMapping("/login")
     public String adminLogin(@RequestBody AdminLogin adminLogin) {
        Admin admin = adminRepository.findByEmail(adminLogin.getEmail());
        if (admin == null || !admin.getPassword().equals(adminLogin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return "Admin logged in successfully";
    }

    // GET mapping to retrieve admin details by adminId
    @GetMapping("/{adminId}")
    public Admin getAdminById(@PathVariable String adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }
}
