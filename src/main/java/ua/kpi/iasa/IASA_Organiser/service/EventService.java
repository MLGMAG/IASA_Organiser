package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.service.data.DataManager;

public class EventService {
    private static EventService instance;
    private final DataManager dataManager = ArrayDataManager.getInstance();

    private EventService() {
    }

    public void createEvent() {
        Event event = new Event();
        dataManager.save(event);
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
            instance = new EventService();
        }
        return instance;
    }
}
