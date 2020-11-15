package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.ArrayDataManager;

import java.util.List;

/**
 * @deprecated It was useful at first lab, but now use {@link DefaultFileDataManager} instead.
 */
@Deprecated
public class DefaultArrayDataManager implements ArrayDataManager {
    private static DefaultArrayDataManager instance;
    private final EventStore data = new EventStore();
    private final static Logger logger = LoggerFactory.getLogger(DefaultArrayDataManager.class);

    private DefaultArrayDataManager() {
    }

    @Override
    public void save(Event event) {
        logger.debug("Saving event with name: '{}'", event.getName());
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


    public boolean checkChanges() {
        return data.isChanged();
    }


    public void backUpChangeFlag() {
        data.setChanged(false);
    }

    @Override
    public Event[] getAllEvents() {
        logger.debug("Returning events from array");
        return data.prototype().getEvents();
    }

    @Override
    public List<Event> getAllEventsList() {
        throw new UnsupportedOperationException("You are working with FileDataManager!");
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

    public static DefaultArrayDataManager getInstance() {
        if (instance == null) {
            logger.debug("Creating instance of {}", DefaultArrayDataManager.class.getSimpleName());
            instance = new DefaultArrayDataManager();
        }
        logger.debug("Returning instance");
        return instance;
    }

    public int getMaxSizeOfData() {
        return data.getMaxSize();
    }
}
