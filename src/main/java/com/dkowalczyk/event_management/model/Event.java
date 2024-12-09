package com.dkowalczyk.event_management.model; // Deklaruje pakiet, do którego należy ta klasa

import jakarta.persistence.*; // Importuje adnotacje JPA do mapowania encji
/**
 * Klasa Event to encja JPA reprezentująca wydarzenie w systemie zarządzania wydarzeniami.
 * Zawiera pola takie jak id, eventName, description, location, date i time,
 * oraz metody akcesorów i mutatorów do manipulacji tymi polami.
 */

@Entity // Oznacza, że klasa jest encją JPA
@Table(name = "event") // Mapuje encję na tabelę w bazie danych o nazwie "event"
public class Event {
    @Id // Oznacza pole jako klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Określa strategię generowania wartości klucza głównego
    private Long id; // Deklaruje pole id jako klucz główny
    @Column(name = "event_name")
    private String eventName; // Deklaruje pole na nazwę wydarzenia
    private String description; // Deklaruje pole na opis wydarzenia
    private String location; // Deklaruje pole na lokalizację wydarzenia
    private String date; // Deklaruje pole na datę wydarzenia
    private String time; // Deklaruje pole na godzinę wydarzenia

    public Event() { // Konstruktor domyślny
    }

    public Event(String eventName, String description, String location, String date, String time) { // Konstruktor z parametrami
        this.eventName = eventName; // Inicjalizuje nazwę wydarzenia
        this.description = description; // Inicjalizuje opis wydarzenia
        this.location = location; // Inicjalizuje lokalizację wydarzenia
        this.date = date; // Inicjalizuje datę wydarzenia
        this.time = time; // Inicjalizuje godzinę wydarzenia
    }

    public Long getId() { // Getter dla id
        return id;
    }

    public String getEventName() { // Getter dla eventName
        return eventName;
    }

    public String getDescription() { // Getter dla description
        return description;
    }

    public String getLocation() { // Getter dla location
        return location;
    }

    public String getDate() { // Getter dla date
        return date;
    }

    public String getTime() { // Getter dla time
        return time;
    }

    public void setEventName(String eventName) { // Setter dla eventName
        this.eventName = eventName;
    }

    public void setDescription(String description) { // Setter dla description
        this.description = description;
    }

    public void setLocation(String location) { // Setter dla location
        this.location = location;
    }

    public void setDate(String date) { // Setter dla date
        this.date = date;
    }

    public void setTime(String time) { // Setter dla time
        this.time = time;
    }
}