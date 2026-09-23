package com.eventmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.entity.Event;
import com.eventmanagement.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
        return new ResponseEntity<>("Event added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable int id) {

        Event event = eventService.getEvent(id);

        if (event == null) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable int id,
                                               @RequestBody Event event) {

        Event oldEvent = eventService.getEvent(id);

        if (oldEvent == null) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }

        event.setId(id);
        eventService.updateEvent(event);

        return ResponseEntity.ok("Event updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {

        Event event = eventService.getEvent(id);

        if (event == null) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }

        eventService.deleteEvent(id);

        return ResponseEntity.ok("Event deleted successfully");
    }
}
