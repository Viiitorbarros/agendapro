package com.agendapro.agendapro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, comum para APIs e H2 console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // PERMITE FRAMES, NECESSÁRIO PARA O H2 CONSOLE
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // PERMITE ACESSO AO H2 CONSOLE
                        .requestMatchers("/agendapro/agendamento/**").permitAll()
                        .anyRequest().authenticated() // Exige autenticação para todo o resto
                );
        // ... suas outras configurações de login, etc.

        return http.build();
    }
}