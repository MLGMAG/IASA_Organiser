package ua.kpi.iasa.IASA_Organiser.service.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.UUID;

public class ArrayDataManager implements DataManager {
    private static ArrayDataManager instance;
    private final EventStore data = new EventStore();
    private final static Logger logger = LoggerFactory.getLogger(ArrayDataManager.class);

    private ArrayDataManager() {
    }

    @Override
    public void save(Event event) {
        logger.debug("Saving event with name: '{}'", event.getName());
        event.setId(UUID.randomUUID());
        if (data.getSize() == data.getMaxSize()) {
            throw new ArrayStoreException("Max size!");
        }
        Event[] events = getAllEvents();
        events[data.getSize()] = event;
        data.setSize(data.getSize() + 1);
        data.setEvents(events);
        data.setChanged(true);
        logger.debug("Successful save; array size: {}, array max_size: {}", data.getSize(), data.getMaxSize());
    }

    @Override
    public boolean checkChanges() {
        return data.isChanged();
    }

    @Override
    public void backUpChangeFlag() {
        data.setChanged(false);
    }

    @Override
    public Event[] getAllEvents() {
        logger.debug("Returning events from array");
        return data.prototype().getEvents();
    }

    @Override
    public void update(Event event) {
        logger.debug("Updating event with id:'{}'", event.getId());
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
        logger.debug("Removing event with name:'{}' and id:'{}'", event.getName(), event.getId());
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
        logger.debug("Successful delete; array size: {}, array max_size: {}", data.getSize(), data.getMaxSize());
    }

    public static ArrayDataManager getInstance() {
        if (instance == null) {
            logger.debug("Creating instance of {}", ArrayDataManager.class.getSimpleName());
            instance = new ArrayDataManager();
        }
        logger.debug("Returning instance");
        return instance;
    }

    public int getMaxSizeOfData() {
        return data.getMaxSize();
    }
}
