package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;

import java.util.List;

@Service
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.repository"})
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

    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
//        filterExpiredEvents();
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
}
