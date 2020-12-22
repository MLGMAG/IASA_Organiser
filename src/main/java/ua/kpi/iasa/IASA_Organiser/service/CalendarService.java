package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultFileDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.model.Type;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class is used for making basic operations on events {@link Event}
 * when we need to consider their state, defined by the state of their fields
 * like {@code date}, {@code time} and {@code priority} {@link Priority} etc.
 * For example, we can find special events, sort Events by given feature
 * and get the sequence of all events with special characteristics given
 * by the user via methods declared in this class.
 * @author Oleksii Kosiuk
 * @author Andrij Makrushin
 * @author Mahomed Akhmedov
 * @see DefaultFileDataManager
 * @see ua.kpi.iasa.IASA_Organiser.controller.Controller
 */
public class CalendarService {
    private static CalendarService instance;
    private final GenericDataManager dataManager = DefaultFileDataManager.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private CalendarService() {
    }

    /**
     * @deprecated uses deprecated class
     */
    @Deprecated(since = "MATA 1.0.0", forRemoval = false)
    public Calendar getCalendar() {
        logger.debug("Method was called...");
        throw new UnsupportedOperationException("You refer to unsupported method");
    }

    /**
     * This method finds all {@link Event} events in data storage with the value of their {@code date} field
     * equal to value searchDate given as a single parameter and returns them.
     * <p>
     * Receives instance of LocalDate {@link LocalDate}.
     * Obtains the list which contains all events stored in data storage {@link DefaultFileDataManager#getFile()}
     * by calling {@link DefaultFileDataManager#getAllEventsList()} method and makes the copy of that list.
     * Then returns list of all events from copied list with value of their date {@code date} field
     * equal to received parameter.
     *
     * @param searchDate instance of LocalDate, events with date field value equal to which we want to find
     * @return list of events with {@code date} field value equal to given parameter
     * @see DefaultFileDataManager
     */
    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        final List<Event> events = getDataManager().getAllEventsList();
        return events.stream()
                .filter(event -> event.getDate().equals(searchDate))
                .collect(Collectors.toList());
    }

    /**
     * This method finds all {@link Event} events in data storage, which have tag{@link Tag} instance equal to single
     * given searchTag parameter in their {@code tags} field which represents the list of tags. And then returns all
     * found events.
     * <p>
     * Receives instance of Tag {@link Tag}.
     * Obtains the list which contains all events stored in data storage {@link DefaultFileDataManager#getFile()}
     * by calling {@link DefaultFileDataManager#getAllEventsList()} method and makes the copy of that list.
     * Then returns list of all events from copied list which have Tag instance equal to received parameter
     * in their tag list {@code tags} field.
     *
     * @param searchTag
     * @return list of events with given tag parameter in their tag list {@code tags} field
     * @see DefaultFileDataManager
     */
    public List<Event> findEventsByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        final List<Event> events = getDataManager().getAllEventsList();
        return events.stream()
                .filter(event -> event.getTags().stream().anyMatch(tag -> tag.equals(searchTag)))
                .collect(Collectors.toList());
    }

    /**
     * This method finds all {@link Event} events in data storage with the value of their {@code priority} field
     * equal to value searchPriority given as a single parameter and returns them.
     * <p>
     * Receives instance of Priority {@link Priority}.
     * Obtains the list which contains all events stored in data storage {@link DefaultFileDataManager#getFile()}
     * by calling {@link DefaultFileDataManager#getAllEventsList()} method and makes the copy of that list.
     * Then returns list of all events from copied list with value of their {@code priority} field
     * equal to received parameter.
     *
     * @param searchPriority instance of Priority, events with priority field value equal to which we want to find
     * @return list of events with {@code priority} field value equal to given parameter
     * @see DefaultFileDataManager
     */
    public List<Event> findEventsByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        final List<Event> events = getDataManager().getAllEventsList();
        return events.stream()
                .filter(event -> event.getPriority().equals(searchPriority))
                .collect(Collectors.toList());
    }

    /**
     * This method sorts all {@link Event} events in data storage by their {@code priority} field and returns the list
     * of sorted events.
     * <p>
     * Obtains the list which contains all events stored in data storage {@link DefaultFileDataManager#getFile()}
     * by calling {@link DefaultFileDataManager#getAllEventsList()} method and makes the copy of that list.
     * Then sorts copied list by {@code priority} and returns it.
     *
     * @return list of events sorted by their {@code priority} field
     * @see DefaultFileDataManager
     */
    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        final List<Event> allEventsList = getDataManager().getAllEventsList();
        allEventsList.sort(Comparator.comparingInt(event -> event.getPriority().getPriority()));
        return allEventsList;
    }

    /**
     * This method sorts all {@link Event} events in list given as a parameter by their {@code date} field and returns
     * the list of sorted events.
     * <p>
     * Receives list of {@link Event} events which should be sorted.
     * Then sorts copied list by {@code date} field and returns it.
     *
     * @param sortedList list of events which should be sorted by {@code date} field
     * @return given as an argument list of events sorted by {@code date}
     */
    public List<Event> sortEventsByDate(List<Event> sortedList) {
        sortedList.sort((ev1, ev2) -> {
            int yearDif = ev1.getDate().getYear() - ev2.getDate().getYear();
            int dayDif = ev1.getDate().getDayOfYear() - ev2.getDate().getDayOfYear();
            if (yearDif != 0) {
                return yearDif;
            } else {
                return dayDif;
            }
        });

        return sortedList;
    }

    /**
     * This method returns all {@link Event} events in data storage which should happen before {@link LocalDate} date
     * given as a parameter.
     * <p>
     * Receives instance of {@link LocalDate}.
     * Obtains the list which contains all events stored in data storage {@link DefaultFileDataManager#getFile()}
     * by calling {@link DefaultFileDataManager#getAllEventsList()} method and makes the copy of that list.
     * Creates new empty list of events and fills it with events which have {@code date} field, which represents the
     * date before date, which is given as a parameter.
     *
     * @param date date, events before which we need to return
     * @return list of events which have {@code date} field, which represents the
     * date before date, which is given as a parameter.
     * @see DefaultFileDataManager
     */
    public List<Event> getEventsBeforeDate(LocalDate date) {
        int year = date.getYear();
        int day = date.getDayOfYear();
        final List<Event> allEventsList = getDataManager().getAllEventsList();
        List<Event> beforeDateList = allEventsList.stream()
                .filter(event -> event.getDate().getYear() <= year && event.getDate().getDayOfYear() <= day)
                .collect(Collectors.toList());

        return sortEventsByDate(beforeDateList);
    }

    /**
     * Find all event, that were expired and not marked as {@link Type#EXPIRED}.
     *
     * @param events - input events
     * @return expired events
     * @author Andrij Makrushin
     * @author Mahomed Akhmedov
     */
    public List<Event> getExpiredEvents(List<Event> events) {
        logger.debug("Method was called with {}", events);
        LocalDate currentDate = getCurrentDate();
        LocalTime currentTime = getCurrentTime();
        return events.stream()
                .filter(event -> !event.getTypes().contains(Type.EXPIRED)).filter(event -> {
                    if (event.getDate().isBefore(currentDate)) {
                        return true;
                    } else return event.getTime().isBefore(currentTime) && event.getDate() == currentDate;
                }).collect(Collectors.toList());
    }

    LocalDate getCurrentDate() {
        logger.debug("Method was called...");
        return LocalDate.now();
    }

    LocalTime getCurrentTime() {
        logger.debug("Method was called...");
        return LocalTime.now();
    }

    public GenericDataManager getDataManager() {
        logger.debug("Method was called...");
        return dataManager;
    }

    public static CalendarService getInstance() {
        logger.debug("Method was called...");
        if (instance == null) {
            logger.debug("Creating instance of {}", CalendarService.class.getSimpleName());
            instance = new CalendarService();
        }
        logger.debug("Returning instance");
        return instance;
    }
}
