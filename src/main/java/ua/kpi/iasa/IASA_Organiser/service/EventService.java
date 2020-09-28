package main.java.ua.kpi.iasa.IASA_Organiser.service;

import main.java.ua.kpi.iasa.IASA_Organiser.model.Event;
import main.java.ua.kpi.iasa.IASA_Organiser.service.data.ArrayDataManager;
import main.java.ua.kpi.iasa.IASA_Organiser.service.data.DataManager;

public class EventService {

    private DataManager dataManager = new ArrayDataManager();

    public boolean createEvent() {
        Event event = new Event();
        return dataManager.save(event);
    }

    public boolean createEvent(Event event) {
        return dataManager.save(event);
    }

    public Event[] getAllEvents() {
        return dataManager.getAllEvents();
    }
}
