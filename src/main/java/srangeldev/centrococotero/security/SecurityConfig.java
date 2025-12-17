package srangeldev.centrococotero.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. PERMITIR ACCESO PÚBLICO (Sin login)
                        // Es vital poner aquí tus CSS, imágenes y la ruta raíz "/"
                        .requestMatchers("/", "/index", "/style1.css", "/images/**", "/js/**", "/webjars/**").permitAll()

                        // 2. PERMITIR VER PRODUCTOS SIN LOGIN
                        .requestMatchers("/producto/**").permitAll()

                        // 3. TODO LO DEMÁS REQUIERE LOGIN (Favoritos, Admin, etc.)
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        // Esto usa la página de login por defecto de Spring, pero permite entrar a ella
                        .permitAll()
                        .defaultSuccessUrl("/", true) // Al loguearte, te manda al inicio
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Al salir, te manda al inicio
                        .permitAll()
                );

        return http.build();
    }
}