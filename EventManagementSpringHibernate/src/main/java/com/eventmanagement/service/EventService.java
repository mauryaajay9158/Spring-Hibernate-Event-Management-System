package com.eventmanagement.service;

import java.util.List;

import com.eventmanagement.entity.Event;

public interface EventService {

    void addEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(int id);

    void updateEvent(Event event);

    void deleteEvent(int id);
}
