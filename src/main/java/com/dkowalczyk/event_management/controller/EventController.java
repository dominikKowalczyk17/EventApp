package com.dkowalczyk.event_management.controller; // Deklaruje pakiet, do którego należy ta klasa

import com.dkowalczyk.event_management.exception.DuplicateEventException; // Importuje klasę wyjątku DuplicateEventException
import com.dkowalczyk.event_management.model.Event; // Importuje klasę modelu Event
import com.dkowalczyk.event_management.repository.EventRepository; // Importuje interfejs EventRepository
import org.springframework.beans.factory.annotation.Autowired; // Importuje adnotację @Autowired
import org.springframework.http.HttpStatus; // Importuje klasę HttpStatus
import org.springframework.http.ResponseEntity; // Importuje klasę ResponseEntity
import org.springframework.web.bind.annotation.*; // Importuje adnotacje Spring MVC

import java.util.List; // Importuje listy z biblioteki Java
import java.util.Optional; // Importuje klasę Optional z biblioteki Java
/**
 * Klasa EventController zarządza operacjami CRUD dla encji Event
 * i definiuje mapowania endpointów API. Obsługuje wyjątki specyficzne
 * dla operacji związanych z wydarzeniami, takie jak DuplicateEventException.
 */

@RestController // Adnotacja, która oznacza, że klasa jest kontrolerem Spring MVC
@RequestMapping("/api/events") // Mapuje żądania HTTP, które zaczynają się od "/api/events"
public class EventController {

    @Autowired // Umożliwia automatyczne wstrzykiwanie zależności
    private EventRepository eventRepository; // Deklaruje pole typu EventRepository

    @PostMapping // Mapuje żądania POST do tej metody
    public Event createEvent(@RequestBody Event event) { // Tworzy nowy event
        if (eventRepository.existsByEventName(event.getEventName())) { // Sprawdza, czy event o takiej samej nazwie już istnieje
            throw new DuplicateEventException("Event with this name already exists."); // Rzuca wyjątek, jeśli istnieje duplikat
        }
        return eventRepository.save(event); // Zapisuje i zwraca nowo utworzony event
    }

    @GetMapping // Mapuje żądania GET do tej metody
    public List<Event> getAllEvents() { // Zwraca listę wszystkich eventów
        return eventRepository.findAll();
    }

    // Read Single Event
    @GetMapping("/{id}") // Mapuje żądania GET do tej metody z identyfikatorem
    public ResponseEntity<Event> getEventById(@PathVariable Long id) { // Pozyskuje event po jego identyfikatorze
        Optional<Event> event = eventRepository.findById(id); // Szuka eventu w repozytorium
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Zwraca znaleziony event lub 404
    }

    // Update Event
    @PutMapping("/{id}") // Mapuje żądania PUT do tej metody
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) { // Aktualizuje istniejący event
        Optional<Event> optionalEvent = eventRepository.findById(id); // Szuka eventu w repozytorium
        if (optionalEvent.isPresent()) { // Sprawdza, czy znaleziono event
            Event event = optionalEvent.get(); // Pobiera obecny event
            event.setEventName(eventDetails.getEventName()); // Aktualizuje nazwę eventu
            event.setDescription(eventDetails.getDescription()); // Aktualizuje opis eventu
            event.setLocation(eventDetails.getLocation()); // Aktualizuje lokalizację eventu
            event.setDate(eventDetails.getDate()); // Aktualizuje datę eventu
            event.setTime(eventDetails.getTime()); // Aktualizuje godzinę eventu
            Event updatedEvent = eventRepository.save(event); // Zapisuje i zwraca zaktualizowany event
            return ResponseEntity.ok(updatedEvent); // Zwraca odpowiedź z zaktualizowanym eventem
        } else {
            return ResponseEntity.notFound().build(); // Zwraca 404, jeśli event nie istnieje
        }
    }

    // Delete Event
    @DeleteMapping("/{id}") // Mapuje żądania DELETE do tej metody
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) { // Usuwa event po jego identyfikatorze
        Optional<Event> optionalEvent = eventRepository.findById(id); // Szuka eventu w repozytorium
        if (optionalEvent.isPresent()) { // Sprawdza, czy znaleziono event
            Event event = optionalEvent.get(); // Pobiera obecny event
            eventRepository.deleteById(id); // Usuwa event z repozytorium
            String responseMessage = "Event deleted successfully: \n" + // Przygotowuje wiadomość zwrotną
                    "Name: " + event.getEventName() + "\n" +
                    "Description: " + event.getDescription() + "\n" +
                    "Location: " + event.getLocation() + "\n" +
                    "Date: " + event.getDate() + "\n" +
                    "Time: " + event.getTime();
            return ResponseEntity.ok(responseMessage); // Zwraca odpowiedź z potwierdzeniem usunięcia
        } else {
            return ResponseEntity.notFound().build(); // Zwraca 404, jeśli event nie istnieje
        }
    }

    @ExceptionHandler(DuplicateEventException.class) // Obsługuje wyjątki typu DuplicateEventException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Ustawia status HTTP na 400
    public String handleDuplicateEventException(DuplicateEventException ex) { // Obsługuje wyjątek DuplicateEventException
        return ex.getMessage(); // Zwraca wiadomość z wyjątku
    }
}