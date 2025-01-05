package com.CabBookingAppApplication.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for REST APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/register", "/user/{userId}", "/user/profile/*","/user/**").permitAll() // Permit access to all user endpoints
                .requestMatchers("/rider/register", "/admin/login").permitAll() // Permit other public endpoints
                .anyRequest().permitAll() // All other requests require authentication
            )
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Ensure stateless session for JWT

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       // auth.inMemoryAuthentication()
         //   .withUser("user")
          //  .password(passwordEncoder().encode("password"))
          //  .roles("USER");
   // }
}
