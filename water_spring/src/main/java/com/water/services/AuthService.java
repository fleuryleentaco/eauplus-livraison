package com.water.services;

import com.water.dto.AuthDTO;
import com.water.models.User;
import com.water.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service pour gérer les opérations d'authentification
 * Contient la logique métier pour l'inscription et la connexion
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Encodeur de mot de passe pour sécuriser le stockage
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Inscrit un nouvel utilisateur dans le système
     * @param registerRequest les informations du nouvel utilisateur
     * @return une réponse indiquant le succès ou l'échec de l'inscription
     */
    public AuthDTO.AuthResponse registerUser(AuthDTO.RegisterRequest registerRequest) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new AuthDTO.AuthResponse(
                    null,
                    null,
                    null,
                    null,
                    null,
                    false,
                    "Cet email est déjà utilisé."
            );
        }

        try {
            // Créer un nouvel utilisateur
            User newUser = new User();
            newUser.setName(registerRequest.getName());
            newUser.setEmail(registerRequest.getEmail());
            // Encoder le mot de passe avant de le stocker
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            newUser.setPhone(registerRequest.getPhone());
            newUser.setAddress(registerRequest.getAddress());
            newUser.setUserType(registerRequest.getUserType());

            // Sauvegarder l'utilisateur dans la base de données
            User savedUser = userRepository.save(newUser);

            // Retourner une réponse de succès
            return new AuthDTO.AuthResponse(
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getUserType(),
                    generateSimpleToken(savedUser.getId()), // Un token basique
                    true,
                    "Inscription réussie!"
            );
        } catch (Exception e) {
            return new AuthDTO.AuthResponse(
                    null,
                    null,
                    null,
                    null,
                    null,
                    false,
                    "Erreur lors de l'inscription: " + e.getMessage()
            );
        }
    }

    /**
     * Connecte un utilisateur existant
     * @param loginRequest les identifiants de connexion
     * @return une réponse contenant les informations utilisateur si authentifié
     */
    public AuthDTO.AuthResponse loginUser(AuthDTO.LoginRequest loginRequest) {
        try {
            // Chercher l'utilisateur par email
            Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                // Vérifier si le mot de passe correspond
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    // Authentification réussie
                    return new AuthDTO.AuthResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getUserType(),
                            generateSimpleToken(user.getId()),
                            true,
                            "Connexion réussie!"
                    );
                } else {
                    // Mot de passe incorrect
                    return new AuthDTO.AuthResponse(
                            null,
                            null,
                            null,
                            null,
                            null,
                            false,
                            "Mot de passe incorrect"
                    );
                }
            } else {
                // Utilisateur non trouvé
                return new AuthDTO.AuthResponse(
                        null,
                        null,
                        null,
                        null,
                        null,
                        false,
                        "Aucun utilisateur trouvé avec cet email"
                );
            }
        } catch (Exception e) {
            return new AuthDTO.AuthResponse(
                    null,
                    null,
                    null,
                    null,
                    null,
                    false,
                    "Erreur lors de la connexion: " + e.getMessage()
            );
        }
    }

    /**
     * Génère un token simple pour l'authentification
     * Note: Dans une application réelle, utilisez JWT ou OAuth2
     * @param userId l'ID de l'utilisateur
     * @return un token basique
     */
    private String generateSimpleToken(String userId) {
        // Une implémentation simple - à remplacer par JWT dans un environnement de production
        return userId + "_" + System.currentTimeMillis();
    }
}