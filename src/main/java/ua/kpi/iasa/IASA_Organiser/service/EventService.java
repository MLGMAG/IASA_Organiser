package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final CalendarService calendarService;

    private final EventRepository eventRepository;

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    public EventService(CalendarService calendarService, EventRepository eventRepository) {
        this.calendarService = calendarService;
        this.eventRepository = eventRepository;
    }

    public void createEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        logger.debug("Method was called...");
        return eventRepository.findAll();
    }

    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventRepository.deleteById(event.getId());
        eventRepository.save(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventRepository.delete(event);
    }

    public void filterExpiredEvents() {
        logger.debug("Method was called...");
        List<Event> currentEvents = eventRepository.findAll();
        List<Event> expiredEvents = calendarService.getExpiredEvents(currentEvents);
        eventRepository.deleteAll(expiredEvents);
    }

    public Event getEventById(UUID id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void createListEvents(List<Event> events) {
        eventRepository.saveAll(events);
    }

    public void deleteById(UUID id) {
        eventRepository.deleteById(id);
    }

    public void deleteListEvent(List<Event> events) {
        eventRepository.deleteAll(events);
    }

    public void updateEvent(UUID id, Event event) {
        if (getEventById(id) == null) {
            return;
        }
        event.setId(id);
        eventRepository.save(event);
    }

}
