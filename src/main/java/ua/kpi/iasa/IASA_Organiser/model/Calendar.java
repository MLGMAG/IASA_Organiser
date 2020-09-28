package main.java.ua.kpi.iasa.IASA_Organiser.model;

public class Calendar {
    private Event[] events;

    public Calendar(Event[] events) {
        this.events = events;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
