package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.EventService;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.service"})
public class EventController {

    private final EventService eventService;

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        return eventService.getAllEventsList();
    }

    public void createNewEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.createEvent(event);
    }

    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.updateEvent(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.removeEvent(event);
    }

}
