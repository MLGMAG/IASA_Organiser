package ua.kpi.iasa.IASA_Organiser.model;

import java.util.Arrays;

public class Calendar {
    private Event[] events;
    private static Calendar instance;

    private Calendar(){
        events = new Event[30];
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public static Calendar getInstance(){
        if(instance == null){
            instance =  new Calendar();
        }
        return instance;
    }

}
