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


/**
 * This class is the main class of the project.
 * It's in charge of validating and verifying the data and transferring it to Model.
 */
public class Controller {
    private final EventService eventService = EventService.getInstance();
    private final CalendarService calendarService = CalendarService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    /**
     * This method makes basic initialization for {@link Controller}.
     * <p>
     * Receives View(the class for displaying data in current MVC arch) instance, calls
     * {@link View#configController(Controller)} and puts received instance to it,
     * calls{@link View#startUp()} method.
     *
     * @param view
     * @see View
     */
    public void init(View view) {
        logger.info("We are starting!");

        view.configController(this);
        view.startUp();
    }

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Deprecated (since = "MATA 1.0.0", forRemoval = false)
    public Event[] getAllEvents() {
        logger.debug("Method was called...");
        return eventService.getAllEvents();
    }

    /**
     * This method gets all events from the dataStorage.
     * <p>
     * Calls {@link EventService#filterExpiredEvents()} for removing all expired events,
     * then calls {@link EventService#getAllEventsList()}.
     *
     * @return list of all events
     * @see EventService
     */
    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        final EventService currentEventService = getEventService();
        currentEventService.filterExpiredEvents();
        return currentEventService.getAllEventsList();
    }

    /**
     * @deprecated It was useful at first lab, but now use {@link #getCalendarService()} instead.
     */
    @Deprecated (since = "MATA 1.0.0", forRemoval = false)
    public Calendar getCalendar() {
        logger.debug("Method was called...");
        return calendarService.getCalendar();
    }

    /**
     * This method requests to save {@link Event} in argument into DB.
     * <p>
     * Receives Event instance, calls {@link EventService#createEvent(Event)} and puts received instance to it.
     *
     * @param event instance of Event that would be saved
     * @see EventService
     */
    public void createNewEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final EventService currentEventService = getEventService();
        currentEventService.createEvent(event);
    }

    /**
     * It request updated {@link Event} to restore it into DB .
     * <p>
     * Receives instance of Event, calls {@link EventService#updateEvent(Event)} and puts received instance to it.
     *
     * @param event instance of Event that would be updated
     * @see EventService
     */
    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final EventService currentEventService = getEventService();
        currentEventService.updateEvent(event);
    }

    /**
     * This method executes {@link Event} deletion on DB.
     * <p>
     * Receives instance of Event, calls {@link EventService#removeEvent(Event)} and puts received instance to it.
     *
     * @param event instance of Event that would be removed
     * @see EventService
     */
    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final EventService currentEventService = getEventService();
        currentEventService.removeEvent(event);
    }

    /**
     * This method gets all events {@link Event} with given date field from the dataStorage.
     * <p>
     * Receives instance of LocalDate, returns result of calling {@link CalendarService#findEventsByDate(LocalDate)}
     * with received instance.
     *
     * @param searchDate
     * @return list of events which have given date field
     * @see CalendarService
     */
    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.findEventsByDate(searchDate);
    }

    /**
     * This method gets all events {@link Event} with given tag field from the dataStorage.
     * <p>
     * Receives instance of Tag, returns result of calling {@link CalendarService#findEventsByTag(Tag)}
     * with received instance.
     *
     * @param searchTag
     * @return list of events which have given tag field
     * @see CalendarService
     */
    public List<Event> findEventByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.findEventsByTag(searchTag);
    }

    /**
     * This method gets all events {@link Event} with given priority field from the dataStorage.
     * <p>
     * Receives instance of Priority, returns result of calling {@link CalendarService#findEventsByPriority(Priority)}
     * with received instance.
     *
     * @param searchPriority
     * @return list of events with given priority field
     * @see CalendarService
     */
    public List<Event> findEventByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.findEventsByPriority(searchPriority);
    }

    /**
     * This method sorts all received events {@link Event} by priority.
     * <p>
     * Calls {@link CalendarService#sortEventsByPriority()} and returns sorted list of events.
     *
     * @return list of events sorted by priority
     * @see CalendarService
     */
    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.sortEventsByPriority();
    }

    EventService getEventService() {
        logger.debug("Method was called...");
        return eventService;
    }

    /**
     * This method sorts all received events(all events in given list) {@link Event} by date.
     * <p>
     * Calls {@link CalendarService#sortEventsByDate(List)} and returns sorted list of events.
     *
     * @param sortedList
     * @return list of events sorted by date
     * @see CalendarService
     */
    public List<Event> sortEventsByDate(List<Event> sortedList) {
        logger.debug("Method was called with {}", sortedList);
        final CalendarService currentCalendarService = getCalendarService();
        return currentCalendarService.sortEventsByDate(sortedList);
    }

    /**
     * This method gets all events {@link Event} which are going to happen before given date.
     * <p>
     * Calls {@link CalendarService#getEventsBeforeDate(LocalDate)}
     *
     * @param date
     * @return list of all events which are planned to be before given date.
     * @see CalendarService
     */
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
