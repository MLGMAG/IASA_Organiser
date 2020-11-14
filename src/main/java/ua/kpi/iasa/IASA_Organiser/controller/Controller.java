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

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Deprecated
    public Event[] getAllEvents() {
        logger.debug("Method was called...");
        return eventService.getAllEvents();
    }

    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        return eventService.getAllEventsList();
    }

    public Calendar getCalendar() {
        logger.debug("Method was called...");
        return calendarService.getCalendar();
    }

    public void createNewEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.createEvent(event);
    }

    public void changeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.updateEvent(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.removeEvent(event);
    }

}
