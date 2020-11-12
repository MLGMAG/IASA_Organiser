package ua.kpi.iasa.IASA_Organiser.service.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import java.util.List;

public interface GenericDataManager {
    void save(Event event);

    void update(Event event);

    void remove(Event event);

    Event[] getAllEvents();

    List<Event> getAllEventsList();

}