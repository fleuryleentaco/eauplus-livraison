package com.water.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Configuration de MongoDB pour Spring Boot
 * Définit la connexion à la base de données MongoDB
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.water.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    /**
     * Définit le nom de la base de données
     * @return le nom de la base de données
     */
    @Override
    protected String getDatabaseName() {
        return "waterdb";
    }

    /**
     * Crée et configure le client MongoDB
     * @return un client MongoDB configuré
     */
    @Override
    public MongoClient mongoClient() {
        // Connexion à une base de données MongoDB locale
        return MongoClients.create("mongodb://localhost:27017");
    }
}