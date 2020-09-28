package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.ConsoleManager;


public class Controller {

    private EventService eventService = new EventService();
    private ConsoleManager consoleManager = new ConsoleManager();
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public boolean createNewEvent(Event event) {
        return eventService.createEvent(event);
    }

    public boolean changeEvent(Event event){    //TODO add functionality!!!
        return true;
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
