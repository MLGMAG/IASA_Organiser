package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.data.impl.DefaultArrayDataManager;

import java.util.Arrays;

public class CalendarService {
    private static CalendarService instance;
    private ArrayDataManager arrayDataManager = DefaultArrayDataManager.getInstance();

    private CalendarService() {
    }

    public static CalendarService getInstance() {
        if (instance == null) {
            instance = new CalendarService();
        }
        return instance;
    }

    public void updateCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setEvents(arrayDataManager.getAllEvents());
    }

    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        if (arrayDataManager.checkChanges()) {
            updateCalendar();
            arrayDataManager.backUpChangeFlag();
        }
        return calendar;
    }

    public Event[] getRecordingsBeforeDate(int year, int month, int day) {    //TODO finish for next ver
        Event[] events = Calendar.getInstance().getEvents();
        Arrays.sort(events);
        int length = 0;
        int evYear = (events[length]).getDate().getYear();
        int evMonth = (events[length]).getDate().getMonthValue();
        int evDay = (events[length]).getDate().getDayOfMonth();
        while ((evYear <= year) && (evMonth <= month) && (evDay <= day)) {
            evYear = (events[length]).getDate().getYear();
            evMonth = (events[length]).getDate().getMonthValue();
            evDay = (events[length]).getDate().getDayOfMonth();
            length++; // count amount all recordings, which are going to happen before some date
        }

        Event[] eventsForCopy = new Event[length];
        for (int i = 0; i < length; i++) {
            eventsForCopy[i] = events[i]; // write all recordings to array
        }
//        Arrays.copyOf()
        return eventsForCopy;
    }
}
