package ua.kpi.iasa.IASA_Organiser.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.List;

public interface GenericDataManager {
    void save(Event event);

    void update(Event event);

    void remove(Event event);

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Deprecated
    Event[] getAllEvents();

    List<Event> getAllEventsList();

}
