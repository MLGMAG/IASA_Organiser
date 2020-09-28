package main.java.ua.kpi.iasa.IASA_Organiser.service.data;

import main.java.ua.kpi.iasa.IASA_Organiser.model.Event;

public class EventStore {
    private Event[] events;
    private final int INIT_SIZE = 10;
    private int size;
    private int maxSize;

    public EventStore() {
        events = new Event[INIT_SIZE];
        size = 0;
        maxSize = INIT_SIZE;
    }

    public Event[] getEvents() {
        return events;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
