package com.dkowalczyk.event_management.repository;

import com.dkowalczyk.event_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventName(String eventName);
}