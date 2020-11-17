package ua.kpi.iasa.IASA_Organiser.data.impl;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.*;

public class DefaultFileDataManager implements FileDataManager {
    private static DefaultFileDataManager instance;
    private File file;
    private List<Event> events;
    private static final String FILE_NAME = "test.txt";
    private static final String STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", FILE_NAME).toString();

    public void init(String path) {
        file = getFile(path);
        events = createNewEventList();
        if (!file.exists()) {
            try {
                initFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            parseData(file, events);
        }
    }

    @Override
    public void save(Event event) {
        List<Event> newEvents = createNewEventList();
        newEvents.addAll(events);
        newEvents.add(event);
        saveAll(newEvents);
    }

    @Override
    public void update(Event event) {
        List<Event> allEventsList = getAllEventsList();
        for (Event eventInList : allEventsList) {
            if (eventInList.getId().compareTo(event.getId()) == 0) {
                allEventsList.set(allEventsList.indexOf(eventInList), event);
                saveAll(allEventsList);
                break;
            }
        }
    }

    @Override
    public void remove(Event event) {
        List<Event> currentEvents = getAllEventsList();
        if (currentEvents.remove(event)) {
            saveAll(currentEvents);
        }
    }

    /**
     * @deprecated It was useful at first lab, but now use {@link #getAllEventsList()} instead.
     */
    @Override
    @Deprecated
    public Event[] getAllEvents() {
        throw new UnsupportedOperationException("You are working with FileDataManager!");
    }

    @Override
    public List<Event> getAllEventsList() {
        return events;
    }

    @Override
    public void saveAll(List<Event> events) {
        List<Event> result = saveListToFile(file, events);
        setEvents(result);
    }

    List<Event> createNewEventList() {
        return new ArrayList<>();
    }

    public static DefaultFileDataManager getInstance() {
        if (instance == null) {
            instance = new DefaultFileDataManager();
            instance.init(STORAGE_PATH);
        }
        return instance;
    }

    private void setEvents(List<Event> events) {
        this.events = events;
    }
}
