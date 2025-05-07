package com.water.controller;

import com.water.dto.AuthDTO;
import com.water.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST exposant les endpoints d'authentification
 * Gère les requêtes d'inscription et de connexion
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint pour l'inscription d'un nouvel utilisateur
     * @param registerRequest les données du nouvel utilisateur
     * @return une réponse HTTP avec le résultat de l'inscription
     */
    @PostMapping("/register")
    public ResponseEntity<AuthDTO.AuthResponse> register(@RequestBody AuthDTO.RegisterRequest registerRequest) {
        AuthDTO.AuthResponse response = authService.registerUser(registerRequest);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Endpoint pour la connexion d'un utilisateur existant
     * @param loginRequest les identifiants de connexion
     * @return une réponse HTTP avec le résultat de la connexion
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDTO.AuthResponse> login(@RequestBody AuthDTO.LoginRequest loginRequest) {
        AuthDTO.AuthResponse response = authService.loginUser(loginRequest);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Endpoint simple pour vérifier si le service d'authentification fonctionne
     * @return un message de bienvenue
     */
    @GetMapping("/test")
    public String testAuth() {
        return "Le service d'authentification est opérationnel!";
    }
}