package com.CabBookingApp;

import com.CabBookingAppApplication.Controllers.UserController;
import com.CabBookingAppApplication.Models.User;
import com.CabBookingAppApplication.Repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    void registerUser_ShouldReturnCreatedUser() throws Exception {
        User user = new User();
        user.setUserId("1");
        user.setName("ak");
        user.setEmail("ak@gmail.com");
        user.setPassword("ak211");
        user.setPhone("9999494949");
        user.setRating("5");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                         .with(csrf())
                        .content("""
                            {
                                "userId": "1",
                                "name": "ak",
                                "email": "ak@gmail.com",
                                "password": "ak211",
                                "phone": "9999494949",
                                "rating" : "5"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("ak"))
                .andExpect(jsonPath("$.email").value("ak@gmail.com"))
                .andExpect(jsonPath("$.phone").value("9999494949"))
                .andExpect(jsonPath("$.rating").value("5"));
    }

    // @WithMockUser(username = "user", roles = {"USER"})
    // @Test
    // void updateUserProfile_ShouldReturnUpdatedUser() throws Exception {
    //     User existingUser = new User();
    //     existingUser.setUserId("1");
    //     existingUser.setName("ak");
    //     existingUser.setPhone("9999494949");

    //     User updatedUser = new User();
    //     updatedUser.setUserId("1");
    //     updatedUser.setName("ak_updated");
    //     updatedUser.setPhone("9999999999");

    //     Mockito.when(userRepository.findById("1")).thenReturn(java.util.Optional.of(existingUser));
    //     Mockito.when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    //     mockMvc.perform(MockMvcRequestBuilders.put("/user/profile/1")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content("""
    //                         {
    //                             "name": "ak_updated",
    //                             "phone": "9999999999"
    //                         }
    //                     """))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.name").value("ak_updated"))
    //             .andExpect(jsonPath("$.phone").value("9999999999"));
    // }

    // @WithMockUser(username = "user", roles = {"USER"})
    // @Test
    // void getUserById_ShouldReturnUser() throws Exception {
    //     User user = new User();
    //     user.setUserId("1");
    //     user.setName("ak");
    //     user.setPhone("9999494949");

    //     Mockito.when(userRepository.findById("1")).thenReturn(java.util.Optional.of(user));

    //     mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.name").value("ak"))
    //             .andExpect(jsonPath("$.phone").value("9999494949"));
    // }

    // @WithMockUser(username = "user", roles = {"USER"})
    // @Test
    // void getUserById_ShouldReturnNotFound() throws Exception {
    //     Mockito.when(userRepository.findById("1")).thenReturn(java.util.Optional.empty());

    //     mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
    //             .andExpect(status().isNotFound());
    // }
}
