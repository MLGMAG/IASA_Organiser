package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.data.impl.DefaultArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.service.data.GenericDataManager;

public class EventService {
    private static EventService instance;
    private final GenericDataManager genericDataManager = DefaultArrayDataManager.getInstance();
    private final static Logger logger = LoggerFactory.getLogger(EventService.class);

    private EventService() {
    }

    public void createEvent(Event event) {
        genericDataManager.save(event);
    }

    public Event[] getAllEvents() {
        return genericDataManager.getAllEvents();
    }

    public void updateEvent(Event event) {
        genericDataManager.update(event);
    }

    public void removeEvent(Event event) {
        genericDataManager.remove(event);
    }

    public static EventService getInstance() {
        if (instance == null) {
            logger.debug("Creating instance of {}", EventService.class.getSimpleName());
            instance = new EventService();
        }
        logger.debug("Returning instance");
        return instance;
    }
}
