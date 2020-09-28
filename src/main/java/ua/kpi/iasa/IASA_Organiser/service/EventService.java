package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.service.data.DataManager;

public class EventService {
    private static EventService instance;
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

    public static EventService getInstance(){
        if(instance == null){
            return new EventService();
        }
        return instance;
    }
}
