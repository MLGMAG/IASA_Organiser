package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.util.FileUtility;

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

/**
 * Default file data manager.
 * <p>
 * Uses {@link FileUtility} in implementation.
 * <p>
 * It contains 2 stage init:
 * {@link DefaultFileDataManager#initState()} and {@link DefaultFileDataManager#initStorage()}.
 * <p>
 * On startup take all data from file and store it in 'cache' {@link DefaultFileDataManager#events}.
 * <p>
 * On all modifications DefaultFileDataManager calls corresponding method to modify cache state
 * and then call {@link DefaultFileDataManager#saveAll(List)} with cache to update file state.
 *
 * @author Mahomed Akhmedov
 * @author Andrij Makrushin
 * @see FileUtility
 */
public class DefaultFileDataManager implements FileDataManager {
    private static DefaultFileDataManager instance;

    /**
     * Instance of file, which identify destination of file, that stores all data.
     */
    private File file;

    /**
     * Cache, which is data transfer object.
     */
    private List<Event> events;

    /**
     * Name of file, which stores all data.
     */
    private static final String FILE_NAME = "defaultFileDataManagerData.txt";

    /**
     * Path to data file.
     */
    public static final String STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data", FILE_NAME).toString();

    private static final Logger logger = LoggerFactory.getLogger(DefaultFileDataManager.class);

    private DefaultFileDataManager() {
    }

    /**
     * First init stage. Initialize state of this class.
     * <p>
     * Creates new file with path with help of {@link FileUtility#getNewFile(String)}
     * and then calls {@link DefaultFileDataManager#setFile(File)}.
     * <p>
     * Initialize cache: create new list {@link DefaultFileDataManager#createNewEventList()}
     * and calls {@link DefaultFileDataManager#setEvents(List)}.
     *
     * @see FileUtility
     */
    public void initState() {
        logger.debug("Initializing state of defaultFileDataManager...");
        final File storage = getNewFile(DefaultFileDataManager.STORAGE_PATH);
        setFile(storage);

        final List<Event> newEventList = createNewEventList();
        setEvents(newEventList);
    }

    /**
     * Second init stage. Initialize cache.
     * <p>
     * If file doesn't exist - creates it with {@link FileUtility#initFile(File)}.
     * Else it gets all info from file by {@link FileUtility#parseData(File, List)}.
     *
     * @see FileUtility
     */
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

    /**
     * Save event to file.
     * <p>
     * It's modify operation. Creates new list with new element
     * and the call {@link DefaultFileDataManager#saveAll(List)}.
     *
     * @param event new event which will be saved
     * @see DefaultFileDataManager#saveAll(List)
     */
    @Override
    public void save(Event event) {
        logger.debug("Method was called with {}", event);
        List<Event> newEvents = createNewEventList();
        final List<Event> currentEvents = getEvents();

        newEvents.addAll(currentEvents);
        newEvents.add(event);
        saveAll(newEvents);
    }

    /**
     * Update existing event.
     * <p>
     * It's modify operation. Search element by {@link java.util.UUID}.
     * On found substitute it and save modified list with help of {@link DefaultFileDataManager#saveAll(List)}.
     *
     * @param event new event which will be updated
     * @see Event#equals(Object)
     * @see DefaultFileDataManager#saveAll(List)
     */
    @Override
    public void update(Event event) {
        logger.debug("Method was called with {}", event);
        List<Event> allEventsList = getEvents();
        for (Event eventInList : allEventsList) {
            if (eventInList.equals(event)) {
                allEventsList.set(allEventsList.indexOf(eventInList), event);
                saveAll(allEventsList);
                break;
            }
        }
    }

    /**
     * Remove existing event.
     * <p>
     * It's modify operation. Remove element from list and save it.
     *
     * @param event new event which will be removed
     * @see Event#equals(Object)
     * @see DefaultFileDataManager#saveAll(List)
     */
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
     * @param events - events that need to be removed
     * @see DefaultFileDataManager#saveAll(List)
     */
    @Override
    public void remove(List<Event> events) {
        logger.debug("Method was called with {}", events);
        List<Event> currentEvents = getEvents();
        List<Event> result = filterEvents(currentEvents, events);
        saveAll(result);
    }


    /**
     * Receives 2 lists and filter one of them.
     *
     * @param excludeEvents - events, which will be removed from {@code allevents}
     * @return list without {@code excludeEvents}
     */
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
    @Deprecated(since = "MATA v1.0.0")
    public Event[] getAllEvents() {
        throw new UnsupportedOperationException("You are working with FileDataManager!");
    }

    /**
     * Returns cached elements.
     */
    @Override
    public List<Event> getAllEventsList() {
        logger.debug("Method was called...");
        return getEvents();
    }

    /**
     * Saves all data into corresponding file.
     * <p>
     * Uses {@link FileUtility#saveListToFile(File, List)} which returns optional.
     * <p>
     * Optional is satisfying a condition of atomicity (one condition of Transaction).
     * If optional is not present returns current cache. Else update old cache with saved list.
     *
     * @param events new events
     * @see FileUtility
     */
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
