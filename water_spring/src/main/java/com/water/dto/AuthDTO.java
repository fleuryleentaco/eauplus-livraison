package com.water.dto;

/**
 * Classe contenant tous les DTOs nécessaires pour l'authentification
 * Utilisée pour structurer les requêtes et réponses liées à l'authentification
 */
public class AuthDTO {

    /**
     * DTO pour la demande d'inscription d'un nouvel utilisateur
     */
    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        private String phone;
        private String address;
        private String userType; // "CLIENT" ou "SUPPLIER"

        // Getters et Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }

    /**
     * DTO pour la demande de connexion d'un utilisateur existant
     */
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters et Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * DTO pour la réponse du serveur après authentification réussie
     */
    public static class AuthResponse {
        private String userId;
        private String name;
        private String email;
        private String userType;
        private String token; // Pour l'authentification JWT (à implémenter plus tard)
        private boolean success;
        private String message;

        // Constructeur pour faciliter la création
        public AuthResponse(String userId, String name, String email, String userType, String token, boolean success, String message) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.userType = userType;
            this.token = token;
            this.success = success;
            this.message = message;
        }

        // Constructeur par défaut
        public AuthResponse() {
        }

        // Getters et Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}