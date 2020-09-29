package ua.kpi.iasa.IASA_Organiser.model;

public class Calendar {
    private Event[] events;
    private static Calendar instance;

    private Calendar(){}

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
