package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        List<Event> events = eventRepository.findAll();
        return sortEventsByPriority(events);
    }

    public void updateEvent(UUID id, Event event) {
        event.setId(id);
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
        Optional<Event> optEvent = eventRepository.findById(id);
        if (optEvent.isEmpty()) {
            return null;
        }
        return optEvent.get();
    }

    public void deleteById(UUID id) {
        eventRepository.deleteById(id);
    }

    List<Event> sortEventsByPriority(List<Event> events) {
        return events.stream()
                .sorted(Comparator.comparing(Event::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public void createListEvents(List<Event> events) {
        eventRepository.saveAll(events);
    }

    public void deleteListEvent(List<Event> events) {
        eventRepository.deleteAll(events);
    }

    public Set<Tag> getEventTags(UUID eventId) {
        Set<Tag> eventTags = eventRepository.findById(eventId).get().getTags();
        if (eventTags == null) {
            return Collections.emptySet();
        }
        return eventTags;
    }

}
