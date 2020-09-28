package main.java.ua.kpi.iasa.IASA_Organiser.service.data;

import main.java.ua.kpi.iasa.IASA_Organiser.model.Event;

public interface DataManager {
    boolean save(Event event);

    Event[] getAllEvents();
}
