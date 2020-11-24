package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultFileDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarService {
    private static CalendarService instance;
    private final GenericDataManager dataManager = DefaultFileDataManager.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private CalendarService() {
    }

    /**
     * @deprecated uses deprecated class
     */
    @Deprecated
    public Calendar getCalendar() {
        logger.debug("Method was called...");
        throw new UnsupportedOperationException("You refer to unsupported method");
    }

    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        final List<Event> events = getDataManager().getAllEventsList();
        return events.stream()
                .filter(event -> event.getDate().equals(searchDate))
                .collect(Collectors.toList());
    }

    public List<Event> findEventsByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        final List<Event> events = getDataManager().getAllEventsList();
        return events.stream()
                .filter(event -> event.getTags().stream().anyMatch(tag -> tag.equals(searchTag)))
                .collect(Collectors.toList());
    }

    public List<Event> findEventsByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        final List<Event> events = getDataManager().getAllEventsList();
        return events.stream()
                .filter(event -> event.getPriority().equals(searchPriority))
                .collect(Collectors.toList());
    }


    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        final List<Event> allEventsList = getDataManager().getAllEventsList();
        allEventsList.sort(Comparator.comparingInt(event -> event.getPriority().getPriority()));
        return allEventsList;
    }

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
     * Find all event, that were expired and not marked as {@link Event#isExpired()}.
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
                .filter(event -> !event.isExpired()).filter(event -> {
                    if (event.getDate().isBefore(currentDate)) {
                        return true;
                    } else return event.getTime().isBefore(currentTime) && event.getDate() == currentDate;
                }).collect(Collectors.toList());
    }

    List<Event> filterByExpired(List<Event> events) {
        return events.stream()
                .filter(event -> !event.isExpired())
                .collect(Collectors.toList());
    }

    List<Event> filterByDate(List<Event> events, LocalDate currentDate) {
        return events.stream()
                .filter(event -> event.getDate().isBefore(currentDate))
                .collect(Collectors.toList());
    }

    List<Event> filterByTime(List<Event> events, LocalTime currentTime) {
        return events.stream()
                .filter(event -> event.getTime().isBefore(currentTime))
                .collect(Collectors.toList());
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
