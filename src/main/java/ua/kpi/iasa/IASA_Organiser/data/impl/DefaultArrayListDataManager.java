package ua.kpi.iasa.IASA_Organiser.data.impl;

import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.List;

public class DefaultArrayListDataManager implements GenericDataManager {

    @Override
    public void save(Event event) {

    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void remove(Event event) {

    }

    @Override
    @Deprecated
    public Event[] getAllEvents() {
        throw new UnsupportedOperationException(); // this method is not supported
    }

    @Override
    public List<Event> getAllEventsList() {
        return null;
    }
}
