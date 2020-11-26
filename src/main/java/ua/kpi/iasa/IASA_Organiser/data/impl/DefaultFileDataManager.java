package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.getNewFile;
import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.initFile;
import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.parseData;
import static ua.kpi.iasa.IASA_Organiser.util.FileUtility.saveListToFile;

public class DefaultFileDataManager implements FileDataManager {
    private static DefaultFileDataManager instance;
    private File file;
    private List<Event> events;
    private static final String FILE_NAME = "defaultFileDataManagerData.txt";
    public static final String STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", FILE_NAME).toString();
    private static final Logger logger = LoggerFactory.getLogger(DefaultFileDataManager.class);

    private DefaultFileDataManager() {
    }

    public void initState() {
        logger.debug("Initializing state of defaultFileDataManager...");
        final File storage = getNewFile(DefaultFileDataManager.STORAGE_PATH);
        setFile(storage);

        final List<Event> newEventList = createNewEventList();
        setEvents(newEventList);
    }

    public void initStorage() {
        logger.debug("Initializing storage of defaultFileDataManager...");
        final File currentFile = getFile();
        if (!currentFile.exists()) {
            logger.debug("File doesn't exist, initializing file...");
            try {
                initFile(currentFile);
            } catch (IOException e) {
                logger.debug("Problems with file initializing: {}", e.getClass().getSimpleName());
                e.printStackTrace();
            }
        } else {
            logger.debug("File exist, start parsing...");
            final List<Event> storage = getEvents();
            parseData(currentFile, storage);
        }
    }

    @Override
    public void save(Event event) {
        logger.debug("Method was called with {}", event);
        List<Event> newEvents = createNewEventList();
        final List<Event> currentEvents = getEvents();

        newEvents.addAll(currentEvents);
        newEvents.add(event);
        saveAll(newEvents);
    }

    @Override
    public void update(Event event) {
        logger.debug("Method was called with {}", event);
        List<Event> allEventsList = getEvents();
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
        logger.debug("Method was called with {}", event);
        List<Event> currentEvents = getEvents();
        if (currentEvents.remove(event)) {
            saveAll(currentEvents);
        }
    }

    /**
     * Should remove all input events from {@link DefaultFileDataManager#events}
     * and call {@link DefaultFileDataManager#saveAll(List)} of the remaining {@link Event}.
     *
     * @param events - events that need to be removed.
     * @author Andrij Makrushin
     * @author Mahomed Akhmedov
     * @see DefaultFileDataManager#saveAll(List)
     */
    @Override
    public void remove(List<Event> events) {
        logger.debug("Method was called with {}", events);
        List<Event> currentEvents = getEvents();
        List<Event> result = filterEvents(currentEvents, events);
        saveAll(result);
    }

    List<Event> filterEvents(List<Event> allEvents, List<Event> excludeEvents) {
        logger.debug("Method was called with all events:{}, excludeEvents:{}", allEvents, excludeEvents);
        return allEvents.stream()
                .filter(e -> !(excludeEvents.contains(e)))
                .collect(Collectors.toList());
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
        logger.debug("Method was called...");
        return getEvents();
    }

    @Override
    public void saveAll(List<Event> events) {
        logger.debug("Method was called with {}", events);
        final File storage = getFile();
        Optional<List<Event>> result = saveListToFile(storage, events);
        result.ifPresent(this::setEvents);
    }

    public static DefaultFileDataManager getInstance() {
        logger.debug("Method was called...");
        if (instance == null) {
            logger.debug("Creating instance of {}", DefaultFileDataManager.class.getSimpleName());
            instance = new DefaultFileDataManager();
            instance.initState();
            instance.initStorage();
        }
        logger.debug("Returning instance");
        return instance;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        logger.debug("Change files, old: '{}', new: '{}'", this.file, file);
        this.file = file;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        logger.debug("Change events, old: {}, new: {}", this.events, events);
        this.events = events;
    }

    List<Event> createNewEventList() {
        return new ArrayList<>();
    }
}
