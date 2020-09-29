package ua.kpi.iasa.IASA_Organiser.service;

public class CalendarService {
    private static CalendarService instance;

    private CalendarService() {
    }

    public static CalendarService getInstance() {
        if (instance == null) {
            instance = new CalendarService();
        }
        return instance;
    }
}
