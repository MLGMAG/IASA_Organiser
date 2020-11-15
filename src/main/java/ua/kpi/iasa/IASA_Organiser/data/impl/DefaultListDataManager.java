package ua.kpi.iasa.IASA_Organiser.data.impl;

import ua.kpi.iasa.IASA_Organiser.data.ListDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.ArrayList;
import java.util.List;

public class DefaultListDataManager implements ListDataManager {
    private List<Event> eventList;

    public DefaultListDataManager() {
        init();
    }

    private void init() {
        setList(new ArrayList<>());
    }


    @Override
    public void save(Event event) {
        eventList.add(event);
    }

    @Override
    public void update(Event event) {
        int index = eventList.indexOf(event);
        eventList.set(index, event);
    }

    @Override
    public void remove(Event event) {
        eventList.remove(event);
    }

    @Override
    public void remove(int index) {
        eventList.remove(index);
    }

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Override
    @Deprecated
    public Event[] getAllEvents() {
        throw new UnsupportedOperationException(); // this method is not supported
    }

    @Override
    public List<Event> getAllEventsList() {
        return this.eventList;
    }

    public void setList(List<Event> list) {
        this.eventList = list;
    }
}
