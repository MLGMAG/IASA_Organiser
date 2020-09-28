package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.service.data.DataManager;

public class EventService {
    private static EventService instance;
    private DataManager dataManager = ArrayDataManager.getInstance();

    private EventService(){}

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

    public static EventService getInstance(){
        if(instance == null){
            return new EventService();
        }
        return instance;
    }

    public void updateEvent(Event event){
        dataManager.update(event);
    }

    public void removeEvent(Event event){
        dataManager.remove(event);
    }
}
