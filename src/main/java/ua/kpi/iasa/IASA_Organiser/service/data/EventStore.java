package ua.kpi.iasa.IASA_Organiser.service.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import java.util.Arrays;

public class EventStore {  //smth like database/it`s foundation
    private Event[] events;
    private final int INIT_SIZE = 30;
    private int size;
    private int maxSize;
    private boolean isChanged;

    public EventStore() {
        events = new Event[INIT_SIZE];
        size = 0;
        maxSize = INIT_SIZE;
    }

    private EventStore(EventStore eventStore){   //constructor for cloning(Prototype pattern)
         this.size = eventStore.size;
         this.maxSize = eventStore.maxSize;
         this.events = new Event[eventStore.events.length];
         this.isChanged = eventStore.isChanged;
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

    public void setChanged(boolean flag){
        isChanged = flag;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public EventStore clone(){
        return new EventStore(this);
    }
}

