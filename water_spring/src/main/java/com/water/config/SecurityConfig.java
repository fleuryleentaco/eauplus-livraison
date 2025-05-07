package com.water.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de Spring Security
 * Pour le moment, nous désactivons la sécurité CSRF et autorisons tous les accès
 * Dans une application réelle, vous devriez configurer une sécurité plus stricte
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure l'encodeur de mot de passe
     * @return un encodeur BCrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure les règles de sécurité de base
     * @param http la configuration de sécurité HTTP
     * @return la chaîne de filtres de sécurité
     * @throws Exception si la configuration échoue
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactive la protection CSRF pour les API REST
                .authorizeRequests()
                .anyRequest().permitAll(); // Autorise toutes les requêtes sans authentification pour le moment

        return http.build();
    }
}