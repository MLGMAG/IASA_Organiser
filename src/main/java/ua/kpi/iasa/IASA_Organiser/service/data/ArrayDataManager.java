package ua.kpi.iasa.IASA_Organiser.service.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.UUID;

public class ArrayDataManager implements DataManager {
    private static ArrayDataManager instance;
    private final EventStore data = new EventStore();

    private ArrayDataManager() {
    }

    @Override
    public void save(Event event) {
        event.setId(UUID.randomUUID());
        if (data.getSize() == data.getMaxSize()) {
            throw new ArrayStoreException("Max size!");
        }
        Event[] events = getAllEvents();
        events[data.getSize()] = event;
        data.setSize(data.getSize() + 1);
        data.setEvents(events);
    }

    @Override
    public Event[] getAllEvents() {
        return data.clone().getEvents();
    }

    @Override
    public void update(Event event) {
        Event[] events = getAllEvents();
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) continue;
            if (events[i].getId().compareTo(event.getId()) == 0) {
                events[i] = event;
                break;
            }
        }
        data.setEvents(events);
    }

    @Override
    public void remove(Event event) {
        Event[] events = getAllEvents();
        for (int i = 0; i < events.length; i++) {
            if (events[i] == null) continue;
            if (events[i].getId().compareTo(event.getId()) == 0) {
                events[i] = null;
                break;
            }
        }
        data.setSize(data.getSize() - 1);
        data.setEvents(events);
    }

    public static ArrayDataManager getInstance() {
        if (instance == null) {
            instance = new ArrayDataManager();
        }
        return instance;
    }
}
