package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.View;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private EventService eventService;
    private CalendarService calendarService;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public void init(View view) {
        logger.info("We are starting!");

        EventService eventServiceInstance = EventService.getInstance();
        setEventService(eventServiceInstance);

        CalendarService calendarServiceInstance = CalendarService.getInstance();
        setCalendarService(calendarServiceInstance);

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

    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.updateEvent(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        eventService.removeEvent(event);
    }

    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        return calendarService.findEventsByDate(searchDate);
    }

    public List<Event> findEventByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        return calendarService.findEventByTag(searchTag);
    }

    public List<Event> findEventByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        return calendarService.findEventByPriority(searchPriority);
    }

    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        return calendarService.sortEventsByPriority();
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }
}
