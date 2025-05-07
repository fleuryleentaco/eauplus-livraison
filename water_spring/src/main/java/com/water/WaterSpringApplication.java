package com.water;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application Eau+ Livraison
 * Cette classe lance le serveur Spring Boot
 */
@SpringBootApplication
public class WaterSpringApplication {

	/**
	 * Méthode principale qui démarre l'application
	 * @param args arguments de ligne de commande
	 */
	public static void main(String[] args) {
		SpringApplication.run(WaterSpringApplication.class, args);
		System.out.println("===== Application Eau+ Livraison démarrée avec succès! =====");
	}
}