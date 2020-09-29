package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.data.ArrayDataManager;
import ua.kpi.iasa.IASA_Organiser.service.data.DataManager;

public class CalendarService {
    private static CalendarService instance;
    private DataManager dataManager = ArrayDataManager.getInstance();

    private CalendarService() {
    }

    public static CalendarService getInstance() {
        if (instance == null) {
            instance = new CalendarService();
        }
        return instance;
    }

    public void updateCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setEvents(dataManager.getAllEvents());
    }

    public Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        if(dataManager.checkChanges()){
            updateCalendar();
            dataManager.backUpChangFlag();
        }
        return calendar;
    }
}
