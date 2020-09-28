package main.java.ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.java.ua.kpi.iasa.IASA_Organiser.model.Event;
import main.java.ua.kpi.iasa.IASA_Organiser.service.EventService;
import main.java.ua.kpi.iasa.IASA_Organiser.view.ConsoleManager;


public class Controller {

    private EventService eventService = new EventService();
    private ConsoleManager consoleManager = new ConsoleManager();
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public boolean createNewEvent(Event event) {
        return eventService.createEvent(event);
    }

    public Controller() {
        logger.info("We are starting!!");
        consoleManager.setController(this);
        consoleManager.startUp();
    }

    public Event[] getAllEvents() {
        return eventService.getAllEvents();
    }
}
