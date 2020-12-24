package ua.kpi.iasa.IASA_Organiser.service;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        validateEventPlace(event);
        eventRepository.save(event);
    }

    void validateEventPlace(Event event) {
        if (event.getPlace().getCountry().equals("")) {
            event.setPlace(null);
        }
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
        deleteListEvent(expiredEvents);
    }

    public Event getEventById(UUID id) {
        Optional<Event> optEvent = eventRepository.findById(id);
        if (optEvent.isEmpty()) {
            return null;
        }
        Event event = optEvent.get();
        event.setInvited(Sets.newHashSet());
        return event;
    }

    public Event getEventAndHumansByEventId(UUID id) {
        return eventRepository.findEventAndHumans(id).orElseGet(() -> getEventById(id));
    }

    public void deleteById(UUID id) {
        Event event = getEventAndHumansByEventId(id);
        cleanEventFromHumans(event);
        eventRepository.deleteById(id);
    }

    void cleanEventFromHumans(Event event) {
        event.getInvited().stream()
                .map(Human::getId)
                .map(humanRepository::findHumanAndEvents)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(human -> removeEventFromHumanAndSaveHuman(event, human));
    }

    void removeEventFromHumanAndSaveHuman(Event event, Human human) {
        human.getEvents().remove(event);
        humanRepository.save(human);
    }

    List<Event> sortEventsByPriority(List<Event> events) {
        return events.stream()
                .sorted(Comparator.comparing(Event::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public void createListEvents(List<Event> events) {
        events.forEach(eventRepository::save);
    }

    public void deleteListEvent(List<Event> events) {
        events.stream().map(Event::getId).forEach(eventRepository::deleteById);
    }

}
