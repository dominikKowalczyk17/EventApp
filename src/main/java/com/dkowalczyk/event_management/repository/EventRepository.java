package com.dkowalczyk.event_management.repository;

import com.dkowalczyk.event_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfejs EventRepository to komponent do interakcji z warstwą bazy danych.
 * Jest to repozytorium JPA, które posiada metody do CRUD dla encji Event,
 * w tym sprawdzanie istnienia wydarzenia o konkretnej nazwie.
 */

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventName(String eventName);
}