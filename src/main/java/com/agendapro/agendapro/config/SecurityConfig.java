package com.agendapro.agendapro.config;

import com.agendapro.agendapro.model.Usuario;
import com.agendapro.agendapro.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Permite H2 Console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera o H2 Console
                        // Regras para Agendamento


                        // PERMITE A PERGUNTA DE "PREFLIGHT" DO CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/agendapro/agendamento/**").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers(HttpMethod.POST, "/agendapro/agendamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/agendapro/agendamento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/agendapro/agendamento/**").hasRole("ADMIN")
                        // Todas as outras requisições precisam de autenticação
                        .anyRequest().authenticated()
                )
                // Usaremos autenticação HTTP Basic por enquanto;
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    CommandLineRunner run(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Se não houver nenhum admin, cria um
            if (usuarioRepository.findByUserName("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUserName("admin");
                admin.setSenha(passwordEncoder.encode("admin123")); // Senha será codificada
                admin.setRole("ADMIN");
                usuarioRepository.save(admin);
            }

            // Se não houver nenhum professor, cria um
            if (usuarioRepository.findByUserName("professor").isEmpty()) {
                Usuario professor = new Usuario();
                professor.setUserName("professor");
                professor.setSenha(passwordEncoder.encode("prof123")); // Senha será codificada
                professor.setRole("PROFESSOR");
                usuarioRepository.save(professor);
            }
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Altere o "*" para o endereço exato do seu Live Server
                        .allowedOrigins("http://127.0.0.1:5500")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }



}