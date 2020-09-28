package ua.kpi.iasa.IASA_Organiser.service.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import java.util.Arrays;

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

    private EventStore(EventStore eventStore){
         this.size = eventStore.size;
         this.maxSize = eventStore.maxSize;
         this.events = new Event[eventStore.events.length];
         for(int i = 0; i < events.length; i++){
             if(eventStore.events[i] == null){
                 this.events[i] = null;
                 continue;
             }
             this.events[i] = eventStore.events[i].clone();
         }
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events){
        this.events = events;
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

    public EventStore clone(){
        return new EventStore(this);
    }
}

