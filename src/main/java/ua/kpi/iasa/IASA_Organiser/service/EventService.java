package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultFileDataManager;

import java.util.List;

public class EventService {
    private static EventService instance;
    private GenericDataManager dataManager;
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    public void init() {
        logger.debug("Initializing eventService...");
        setDataManager(DefaultFileDataManager.getInstance());
    }

    public void createEvent(Event event) {
        logger.debug("Method was called with {}", event);
        dataManager.save(event);
    }

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Deprecated
    public Event[] getAllEvents() {
        logger.debug("Method was called...");
        return dataManager.getAllEvents();
    }

    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        return dataManager.getAllEventsList();
    }

    public void updateEvent(Event event) {
        logger.debug("Method was called with {}", event);
        dataManager.update(event);
    }

    public void removeEvent(Event event) {
        logger.debug("Method was called with {}", event);
        dataManager.remove(event);
    }

    public void setDataManager(GenericDataManager dataManager) {
        logger.debug("Setting DataManager {}", dataManager.getClass().getSimpleName());
        this.dataManager = dataManager;
    }

    public static EventService getInstance() {
        logger.debug("Method was called...");
        if (instance == null) {
            logger.debug("Creating instance of {}", EventService.class.getSimpleName());
            instance = new EventService();
            instance.init();
        }
        logger.debug("Returning instance");
        return instance;
    }
}
