package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(u -> u
                        .requestMatchers("/login", "/error").permitAll()
                        .requestMatchers("/user", "/api/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**", "/api/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
               .formLogin(form -> form
                       .successHandler(successUserHandler)
                       .permitAll()
               )
               .logout(logout -> logout
                       .permitAll()
                       .logoutSuccessUrl("/login")
               ).build();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}