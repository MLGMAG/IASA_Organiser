package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.View;


public class Controller {
    private final EventService eventService = EventService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(View view) {
        logger.info("We are starting!");
        view.configController(this);
        view.startUp();
    }

    public Event[] getAllEvents() {
        return eventService.getAllEvents();
    }

    public void createNewEvent(Event event) {
        eventService.createEvent(event);
    }

    public void changeEvent(Event event) {    //TODO add functionality!!!
        eventService.updateEvent(event);
    }

    public void removeEvent(Event event) {
        eventService.removeEvent(event);
    }
}
