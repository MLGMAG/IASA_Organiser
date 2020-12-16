package ua.kpi.iasa.IASA_Organiser.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/events")
@ComponentScan(basePackages = "ua.kpi.iasa.IASA_Organiser.service")
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable UUID id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/")
    public void createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
    }

    @PostMapping("/list")
    public void createListEvent(@RequestBody List<Event> events) {
        eventService.createListEvents(events);
    }

    @DeleteMapping("/{id}")
    public void removeEvent(@PathVariable UUID id) {
        eventService.deleteById(id);
    }

    @DeleteMapping("/list")
    public void deleteListHuman(@RequestBody List<Event> events) {
        eventService.deleteListEvent(events);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable UUID id, @RequestBody Event event) {
        eventService.updateEvent(id, event);
    }

}
