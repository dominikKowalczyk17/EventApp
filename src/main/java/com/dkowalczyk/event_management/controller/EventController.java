package com.dkowalczyk.event_management.controller;

import com.dkowalczyk.event_management.exception.DuplicateEventException;
import com.dkowalczyk.event_management.model.Event;
import com.dkowalczyk.event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        if (eventRepository.existsByEventName(event.getEventName())) {
            throw new DuplicateEventException("Event with this name already exists.");
        }
        return eventRepository.save(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Read Single Event
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setEventName(eventDetails.getEventName());
            event.setDescription(eventDetails.getDescription());
            event.setLocation(eventDetails.getLocation());
            event.setDate(eventDetails.getDate());
            event.setTime(eventDetails.getTime());
            Event updatedEvent = eventRepository.save(event);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Event
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            eventRepository.deleteById(id);
            String responseMessage = "Event deleted successfully: \n" +
                    "Name: " + event.getEventName() + "\n" +
                    "Description: " + event.getDescription() + "\n" +
                    "Location: " + event.getLocation() + "\n" +
                    "Date: " + event.getDate() + "\n" +
                    "Time: " + event.getTime();
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(DuplicateEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicateEventException(DuplicateEventException ex) {
        return ex.getMessage();
    }
}