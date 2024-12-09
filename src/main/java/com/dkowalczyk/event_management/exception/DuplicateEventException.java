package com.dkowalczyk.event_management.exception; // Deklaruje pakiet, do którego należy ta klasa
/**
 * Klasa DuplicateEventException reprezentuje wyjątek specyficzny dla
 * operacji związanych z duplikacją wydarzeń. Jest używana do
 * zgłaszania błędów, gdy użytkownik próbuje dodać wydarzenie z nazwą,
 * która już istnieje w systemie.
 */

public class DuplicateEventException extends RuntimeException{ // Definiuje klasę wyjątku, która dziedziczy po RuntimeException
    public DuplicateEventException(String message) { // Konstruktor z parametrem message
        super(message); // Wywołuje konstruktor klasy bazowej z wiadomością
    }
}