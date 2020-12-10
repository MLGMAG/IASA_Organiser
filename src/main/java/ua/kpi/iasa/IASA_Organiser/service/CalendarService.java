package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.model.Type;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = {"ua.kpi.iasa.IASA_Organiser.repository"})
public class CalendarService {

    private final EventRepository eventRepository;

    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    public CalendarService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        final List<Event> events = eventRepository.findAll();
        return events.stream()
                .filter(event -> event.getDate().equals(searchDate))
                .collect(Collectors.toList());
    }

    public List<Event> findEventsByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        final List<Event> events = eventRepository.findAll();
        return events.stream()
                .filter(event -> event.getTags().stream().anyMatch(tag -> tag.equals(searchTag)))
                .collect(Collectors.toList());
    }

    public List<Event> findEventsByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        final List<Event> events = eventRepository.findAll();
        return events.stream()
                .filter(event -> event.getPriority().equals(searchPriority))
                .collect(Collectors.toList());
    }

    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        final List<Event> allEventsList = eventRepository.findAll();
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
        final List<Event> allEventsList = eventRepository.findAll();

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
}
