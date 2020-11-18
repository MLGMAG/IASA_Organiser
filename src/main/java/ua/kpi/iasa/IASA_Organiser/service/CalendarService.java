package ua.kpi.iasa.IASA_Organiser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.iasa.IASA_Organiser.controller.Controller;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultFileDataManager;
import ua.kpi.iasa.IASA_Organiser.model.*;
import ua.kpi.iasa.IASA_Organiser.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultArrayDataManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarService {
    private static CalendarService instance;
    private GenericDataManager dataManager = DefaultFileDataManager.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private CalendarService() {
    }

    public Calendar getCalendar() {
        logger.debug("Method was called...");
        throw new UnsupportedOperationException("You refer to unsupported method");
    }

    public List<Event> findEventsByDate(LocalDate searchDate) {
        logger.debug("Method was called with {}", searchDate);
        return dataManager.getAllEventsList().stream()
                .filter(event -> event.getDate().equals(searchDate))
                .collect(Collectors.toList());
    }

    public List<Event> findEventByTag(Tag searchTag) {
        logger.debug("Method was called with {}", searchTag);
        return dataManager.getAllEventsList().stream()
                .filter(event -> event.getTags().stream().anyMatch(tag -> tag.equals(searchTag)))
                .collect(Collectors.toList());
    }

    public List<Event> findEventByPriority(Priority searchPriority) {
        logger.debug("Method was called with {}", searchPriority);
        return dataManager.getAllEventsList().stream()
                .filter(event -> event.getPriority().equals(searchPriority))
                .collect(Collectors.toList());
    }


    public List<Event> sortEventsByPriority() {
        logger.debug("Method was called...");
        List<Event> allEventsList = dataManager.getAllEventsList();
        allEventsList.sort(Comparator.comparingInt(event -> event.getPriority().getPriority()));
        return allEventsList;
    }

    public static CalendarService getInstance() {
        if (instance == null) {
            instance = new CalendarService();
        }
        return instance;
    }
}
