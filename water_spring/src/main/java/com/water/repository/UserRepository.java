package com.water.repository;

import com.water.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Interface pour l'accès aux données utilisateur dans MongoDB
 * Étend MongoRepository pour hériter des méthodes CRUD de base
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Trouve un utilisateur par son email
     * @param email l'email de l'utilisateur recherché
     * @return l'utilisateur correspondant (ou vide si non trouvé)
     */
    Optional<User> findByEmail(String email);

    /**
     * Vérifie si un utilisateur existe déjà avec cet email
     * @param email l'email à vérifier
     * @return true si un utilisateur avec cet email existe, false sinon
     */
    boolean existsByEmail(String email);

    /**
     * Trouve un utilisateur par son email et son mot de passe
     * Note: En production, évitez cette méthode et préférez vérifier le mot de passe après avoir trouvé l'utilisateur
     * @param email l'email de l'utilisateur
     * @param password le mot de passe à vérifier
     * @return l'utilisateur correspondant (ou vide si non trouvé/mot de passe incorrect)
     */
    Optional<User> findByEmailAndPassword(String email, String password);
}