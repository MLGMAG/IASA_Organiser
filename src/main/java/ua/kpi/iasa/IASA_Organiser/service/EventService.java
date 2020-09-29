package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.service.data.DataManager;

public class EventService {
    private static EventService instance;
    private final DataManager dataManager = ArrayDataManager.getInstance();
    private final static Logger logger = LoggerFactory.getLogger(EventService.class);

    private EventService() {
    }

    public void createEvent(Event event) {
        dataManager.save(event);
    }

    public Event[] getAllEvents() {
        return dataManager.getAllEvents();
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
