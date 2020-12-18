package ua.kpi.iasa.IASA_Organiser.service;

import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

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

    private final HumanRepository humanRepository;

    public EventService(CalendarService calendarService, EventRepository eventRepository, HumanRepository humanRepository) {
        this.calendarService = calendarService;
        this.eventRepository = eventRepository;
        this.humanRepository = humanRepository;
    }

    public void createEvent(Event event) {
        if (event.getPlace().getCountry().equals("")) {
            event.setPlace(null);
        }
        eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return sortEventsByPriority(events);
    }

    public void updateEvent(UUID id, Event event) {
        event.setId(id);
        eventRepository.save(event);
    }

    public void removeEvent(Event event) {
        eventRepository.delete(event);
    }

    public void filterExpiredEvents() {
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
        Optional<Event> optEvent = eventRepository.findById(id);
        if (optEvent.isEmpty()) {
            return;
        }
        Event event = optEvent.get();
        if (event.getInvited().isEmpty()) {
            eventRepository.deleteById(id);
            return;
        }
        List<Human> allHumans = humanRepository.findAllById(event.getInvited().stream().map(Human::getId).collect(Collectors.toSet()));
        allHumans.forEach(human -> {
            Set<Event> events = human.getEvents().stream().filter(event1 -> !event1.getId().equals(event.getId())).collect(Collectors.toSet());
            human.setEvents(events);
            humanRepository.save(human);
        });
        event.setInvited(Collections.emptySet());
        eventRepository.save(event);
        eventRepository.delete(event);
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

}
