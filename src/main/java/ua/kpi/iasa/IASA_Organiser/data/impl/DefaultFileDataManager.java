package ua.kpi.iasa.IASA_Organiser.data.impl;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.getNewFile;
import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.initFile;
import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.parseData;
import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.saveListToFile;


public class DefaultFileDataManager implements FileDataManager {
    private static DefaultFileDataManager instance;
    private File file;
    private List<Event> events;
    private static final String FILE_NAME = "test.txt";
    private static final String STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", FILE_NAME).toString();

    private void initState() {
        final File storage = getNewFile(DefaultFileDataManager.STORAGE_PATH);
        setFile(storage);

        final List<Event> newEventList = createNewEventList();
        setEvents(newEventList);
    }

    public void initStorage() {
        if (!getFile().exists()) {
            try {
                final File storage = getFile();
                initFile(storage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            final List<Event> storage = getAllEventsList();
            final File parseFile = getFile();
            parseData(parseFile, storage);
        }
    }

    @Override
    public void save(Event event) {
        List<Event> newEvents = createNewEventList();
        final List<Event> currentEvents = getAllEventsList();

        newEvents.addAll(currentEvents);
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
        return getEvents();
    }

    @Override
    public void saveAll(List<Event> events) {
        final File storage = getFile();
        List<Event> result = saveListToFile(storage, events);
        setEvents(result);
    }

    List<Event> createNewEventList() {
        return new ArrayList<>();
    }

    public static DefaultFileDataManager getInstance() {
        if (instance == null) {
            instance = new DefaultFileDataManager();
            instance.initState();
            instance.initStorage();
        }
        return instance;
    }

    public File getFile() {
        return file;
    }

    private void setFile(File file) {
        this.file = file;
    }

    public List<Event> getEvents() {
        return events;
    }

    private void setEvents(List<Event> events) {
        this.events = events;
    }
}
