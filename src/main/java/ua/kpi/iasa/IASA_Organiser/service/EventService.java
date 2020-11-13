package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultFileDataManager;

import java.util.List;

public class EventService {
    private static EventService instance;
    private final GenericDataManager dataManager = DefaultFileDataManager.getInstance();
    private final static Logger logger = LoggerFactory.getLogger(EventService.class);

    private EventService() {
    }

    public void createEvent(Event event) {
        dataManager.save(event);
    }

    @Deprecated
    public Event[] getAllEvents() {
        return dataManager.getAllEvents();
    }

    public List<Event> getAllEventsList() {
        return dataManager.getAllEventsList();
    }

    public void updateEvent(Event event) {
        dataManager.update(event);
    }

    public void removeEvent(Event event) {
        dataManager.remove(event);
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
