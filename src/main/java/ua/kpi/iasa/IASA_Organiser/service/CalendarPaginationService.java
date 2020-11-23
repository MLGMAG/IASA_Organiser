package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarPaginationService {

    private static CalendarPaginationService instance;
    private final CalendarService calendarService = CalendarService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private CalendarPaginationService() {
    }

    public List<Event> getCurrentDay() {
        LocalDate currentDate = getCurrentDate();
        final List<Event> events = getDataManager().getAllEventsList();
        int curYear = currentDate.getYear();
        int curDay = currentDate.getDayOfYear();
        return events.stream()
                .filter(event -> (event.getDate().getDayOfYear() == curDay &&
                        event.getDate().getYear() == curYear))
                .collect(Collectors.toList());
    }

    public List<Event> getCurrentWeek() {
        LocalDate currentDate = getCurrentDate();
        final List<Event> events = getDataManager().getAllEventsList();
        int curYear = currentDate.getYear();
        int curDay = currentDate.getDayOfYear();
        int curDayOfWeek = currentDate.getDayOfWeek().getValue();
        int startOfWeekDay = curDay - (curDayOfWeek - 1);
        int endOfWeekDay = curDay + (7 - curDayOfWeek);
        return events.stream()
                .filter(event -> (event.getDate().getDayOfYear() >= startOfWeekDay &&
                        event.getDate().getDayOfYear() <= endOfWeekDay &&
                        event.getDate().getYear() == curYear))
                .collect(Collectors.toList());
    }

    public List<Event> getCurrentMonth() {
        LocalDate currentDate = getCurrentDate();
        final List<Event> events = getDataManager().getAllEventsList();
        int curYear = currentDate.getYear();
        int curMonth = currentDate.getMonthValue();
        return events.stream()
                .filter(event -> (event.getDate().getMonthValue() == curMonth &&
                        event.getDate().getYear() == curYear))
                .collect(Collectors.toList());
    }

    public CalendarService getCalendarService() {
        return calendarService;
    }

    public GenericDataManager getDataManager() {
        logger.debug("Method was called...");
        return calendarService.getDataManager();
    }

    LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static CalendarPaginationService getInstance() {
        logger.debug("Method was called...");
        if (instance == null) {
            logger.debug("Creating instance of {}", CalendarPaginationService.class.getSimpleName());
            instance = new CalendarPaginationService();
        }
        logger.debug("Returning instance");
        return instance;
    }
}
