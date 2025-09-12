package com.example.cashcard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/cashcards/**")
                        .hasRole("card-owner"))
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
        var builder = User.builder();
        UserDetails sarah = builder
                .username("sarah")
                .password(passwordEncoder.encode("password"))
                .roles("card-owner")
                .build();
        UserDetails hank = builder
                .username("hank")
                .password(passwordEncoder.encode("secret"))
                .roles("non-owner")
                .build();
        UserDetails kumar = builder
                .username("kumar")
                .password(passwordEncoder.encode("hidden"))
                .roles("card-owner")
                .build();
        return new InMemoryUserDetailsManager(sarah, hank, kumar);
    }
}