package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.service", "ua.kpi.iasa.IASA_Organiser.repository"})
public class CalendarPaginationService {

    private final EventRepository eventRepository;

    private final CalendarService calendarService;

    private static final Logger logger = LoggerFactory.getLogger(CalendarPaginationService.class);

    public CalendarPaginationService(EventRepository eventRepository, CalendarService calendarService) {
        this.eventRepository = eventRepository;
        this.calendarService = calendarService;
    }

    public List<Event> getCurrentDay() {
        LocalDate currentDate = getCurrentDate();
        final List<Event> events = eventRepository.findAll();
        int curYear = currentDate.getYear();
        int curDay = currentDate.getDayOfYear();
        return events.stream()
                .filter(event -> (event.getDate().getDayOfYear() == curDay &&
                        event.getDate().getYear() == curYear))
                .collect(Collectors.toList());
    }

    public List<Event> getCurrentWeek() {
        LocalDate currentDate = getCurrentDate();
        final List<Event> events = eventRepository.findAll();
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
        final List<Event> events = eventRepository.findAll();
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

    LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
