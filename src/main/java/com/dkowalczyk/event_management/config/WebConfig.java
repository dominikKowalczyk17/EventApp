package com.dkowalczyk.event_management.config; // Deklaruje pakiet, do którego należy ta klasa

import org.springframework.context.annotation.Bean; // Importuje adnotację @Bean
import org.springframework.context.annotation.Configuration; // Importuje adnotację @Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry; // Importuje klasę CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Importuje interfejs WebMvcConfigurer
/**
 * Klasa WebConfig konfiguruje ustawienia dla aplikacji webowych w Springu.
 * Definiuje bean WebMvcConfigurer do ustawień CORS, co pozwala na kontrolę
 * dostępu do zasobów serwera z różnych domen.
 */

@Configuration // Oznacza klasę jako konfigurację Springa
public class WebConfig {

    @Bean // Oznacza metodę jako definiującą beana
    public WebMvcConfigurer corsConfigurer() { // Definiuje konfigurację dla CORS
        return new WebMvcConfigurer() { // Tworzy i zwraca WebMvcConfigurer
            @Override
            public void addCorsMappings(CorsRegistry registry) { // Nadpisuje metodę do dodawania mapowań CORS
                registry.addMapping("/api/**") // Dodaje mapowanie URL, dla którego CORS jest konfigurowany
                        .allowedOrigins("http://localhost:5173") // Definiuje dozwolone źródło żądań
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Definiuje dozwolone metody HTTP
                        .allowedHeaders("*") // Pozwala na wszystkie nagłówki
                        .allowCredentials(true); // Zezwala na poświadczenia
            }
        };
    }
}