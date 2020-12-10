package ua.kpi.iasa.IASA_Organiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;

import java.time.LocalDate;
import java.util.List;

@RestController
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.service"})
public class CalendarController {

    private final CalendarService calendarService;

    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        return calendarService.findEventsByDate(searchDate);
    }

    public List<Event> findEventsByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        return calendarService.findEventsByTag(searchTag);
    }

    public List<Event> findEventByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        return calendarService.findEventsByPriority(searchPriority);
    }

    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        return calendarService.sortEventsByPriority();
    }

    public List<Event> sortEventsByDate(List<Event> sortedList) {
        logger.debug("Method was called with {}", sortedList);
        return calendarService.sortEventsByDate(sortedList);
    }

    public List<Event> getEventsBeforeDate(LocalDate date) {
        logger.debug("Method was called with {}", date);
        return calendarService.getEventsBeforeDate(date);
    }

}
