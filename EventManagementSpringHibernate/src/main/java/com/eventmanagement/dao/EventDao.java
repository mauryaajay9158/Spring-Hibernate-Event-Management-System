package com.eventmanagement.dao;

import java.util.List;

import com.eventmanagement.entity.Event;

public interface EventDao {

    void save(Event event);

    List<Event> getAllEvents();

    Event getEventById(int id);

    void update(Event event);

    void delete(int id);
}
