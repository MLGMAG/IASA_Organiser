package ua.kpi.iasa.IASA_Organiser.controller;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.ConsoleManager;


public class Controller {
    private final EventService eventService = EventService.getInstance();
    private final ConsoleManager consoleManager;

    public Controller() {
        consoleManager = new ConsoleManager(this);
        consoleManager.startUp();
    }

    public Event[] getAllEvents() {
        return eventService.getAllEvents();
    }

    public void createNewEvent(Event event) {
        eventService.createEvent(event);
    }

    public void changeEvent(Event event){    //TODO add functionality!!!
        eventService.updateEvent(event);
    }

    public void removeEvent(Event event){
        eventService.removeEvent(event);
    }
}
