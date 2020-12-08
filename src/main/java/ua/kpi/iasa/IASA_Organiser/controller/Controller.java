package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.View;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Controller
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.service"})
public class Controller {

    private final EventService eventService;

    private final CalendarService calendarService;

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(EventService eventService, CalendarService calendarService) {
        this.eventService = eventService;
        this.calendarService = calendarService;
    }

    public void init(View view) {
        logger.info("We are starting!");

        view.configController(this);
        view.startUp();
    }

    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        final EventService currentEventService = getEventService();
        currentEventService.filterExpiredEvents();
        return currentEventService.getAllEventsList();
    }

    public void createNewEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final EventService currentEventService = getEventService();
        currentEventService.createEvent(event);
    }

    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final EventService currentEventService = getEventService();
        currentEventService.updateEvent(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final EventService currentEventService = getEventService();
        currentEventService.removeEvent(event);
    }

    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.findEventsByDate(searchDate);
    }

    public List<Event> findEventByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.findEventsByTag(searchTag);
    }

    public List<Event> findEventByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.findEventsByPriority(searchPriority);
    }

    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.sortEventsByPriority();
    }

    EventService getEventService() {
        logger.debug("Method was called...");
        return eventService;
    }

    public List<Event> sortEventsByDate(List<Event> sortedList) {
        logger.debug("Method was called with {}", sortedList);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.sortEventsByDate(sortedList);
    }

    public List<Event> getEventsBeforeDate(LocalDate date) {
        logger.debug("Method was called with {}", date);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.getEventsBeforeDate(date);
    }

    CalendarService getCalendarService() {
        logger.debug("Method was called...");
        return calendarService;
    }
}
