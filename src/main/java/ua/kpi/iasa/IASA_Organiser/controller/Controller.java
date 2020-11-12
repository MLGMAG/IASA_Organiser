package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.View;

import java.util.List;


public class Controller {
    private final EventService eventService = EventService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final CalendarService calendarService = CalendarService.getInstance();

    public Controller(View view) {
        logger.info("We are starting!");
        view.configController(this);
        view.startUp();
    }
    @Deprecated
    public Event[] getAllEvents() {     //was needed in the last lab, now useless
        return eventService.getAllEvents();
    }

    public List<Event> getAllEventsList(){
        return eventService.getAllEventsList();
    }

    public Calendar getCalendar(){
        return calendarService.getCalendar();
    }

    public void createNewEvent(Event event) {
        System.out.println(event);
        eventService.createEvent(event);
    }

    public void changeEvent(Event event) {
        eventService.updateEvent(event);
    }

    public void removeEvent(Event event) {
        eventService.removeEvent(event);
    }

}
