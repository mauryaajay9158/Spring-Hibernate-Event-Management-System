package com.eventmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.entity.Event;

@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Event event) {
        getSession().save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return getSession()
                .createQuery("from Event", Event.class)
                .getResultList();
    }

    @Override
    public Event getEventById(int id) {
        return getSession().get(Event.class, id);
    }

    @Override
    public void update(Event event) {
        getSession().update(event);
    }

    @Override
    public void delete(int id) {
        Event event = getEventById(id);

        if (event != null) {
            getSession().delete(event);
        }
    }
}
