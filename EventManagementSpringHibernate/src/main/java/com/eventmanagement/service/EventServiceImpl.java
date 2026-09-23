package com.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eventmanagement.dao.EventDao;
import com.eventmanagement.entity.Event;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional
    public void addEvent(Event event) {
        eventDao.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEvent(int id) {
        return eventDao.getEventById(id);
    }

    @Override
    @Transactional
    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    @Override
    @Transactional
    public void deleteEvent(int id) {
        eventDao.delete(id);
    }
}
