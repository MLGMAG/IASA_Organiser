package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.List;

@Service
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.data.impl"})
public class EventService {

    private final CalendarService calendarService;

    private final FileDataManager dataManager;

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    public EventService(CalendarService calendarService, FileDataManager dataManager) {
        this.calendarService = calendarService;
        this.dataManager = dataManager;
    }

    public void createEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final FileDataManager currentDataManager = getDataManager();
        currentDataManager.save(event);
    }

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Deprecated
    public Event[] getAllEvents() {
        logger.debug("Method was called...");
        final FileDataManager currentDataManager = getDataManager();
        return currentDataManager.getAllEvents();
    }

    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        final FileDataManager currentDataManager = getDataManager();
        return currentDataManager.getAllEventsList();
    }

    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final FileDataManager currentDataManager = getDataManager();
        currentDataManager.update(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        final FileDataManager currentDataManager = getDataManager();
        currentDataManager.remove(event);
    }

    /**
     * Should get events from {@link GenericDataManager#getAllEventsList()},
     * then get expired events by {@link CalendarService#getExpiredEvents(List)}
     * and finally call {@link FileDataManager#remove(List)} of the expired {@link Event}.
     *
     * @author Andrij Makrushin
     * @author Mahomed Akhmedov
     * @see CalendarService#getExpiredEvents(List)
     * @see FileDataManager#remove(List)
     */
    public void filterExpiredEvents() {
        logger.debug("Method was called...");
        final FileDataManager currentFileDataManager = getDataManager();
        final List<Event> currentEvents = currentFileDataManager.getAllEventsList();
        final CalendarService currentCalendarService = getCalendarService();
        final List<Event> expiredEvents = currentCalendarService.getExpiredEvents(currentEvents);
        currentFileDataManager.remove(expiredEvents);
    }

    CalendarService getCalendarService() {
        logger.debug("Method was called...");
        return calendarService;
    }

    FileDataManager getDataManager() {
        logger.debug("Method was called...");
        return dataManager;
    }
}
