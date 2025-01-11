package com.assesment.currency_cal_exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryAuthConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("employee")
                .password(passwordEncoder.encode("password1"))
                .roles("EMPLOYEE") // Role-based access (optional)
                .build();

        UserDetails user2 = User.withUsername("admin")
                .password(passwordEncoder.encode("password2"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}

