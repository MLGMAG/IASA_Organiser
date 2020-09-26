package ua.kpi.iasa.IASA_Organiser.service.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.UUID;

public class ArrayDataManager implements DataManager {

    private EventStore data = new EventStore();
    private static final Logger logger = LoggerFactory.getLogger(ArrayDataManager.class);

    @Override
    public boolean save(Event event) {
        logger.debug("Saving event object: {}", event);
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
