package ua.kpi.iasa.IASA_Organiser.service.data;

import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.UUID;

public class ArrayDataManager implements DataManager {

    private EventStore data = new EventStore();

    @Override
    public boolean save(Event event) {
        event.setId(UUID.randomUUID());
        if (data.getSize() == data.getMaxSize()) {
            return false;
        }
        data.getEvents()[data.getSize()] = event;
        data.setSize(data.getSize() + 1);
        return true;
    }

    @Override
    public Event[] getAllEvents() {
        return data.getEvents();
    }
}
