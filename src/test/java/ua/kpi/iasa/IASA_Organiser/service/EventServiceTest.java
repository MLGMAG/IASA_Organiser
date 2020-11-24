package ua.kpi.iasa.IASA_Organiser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
import ua.kpi.iasa.IASA_Organiser.service.EventService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private GenericDataManager genericDataManager;

    @Mock
    private FileDataManager fileDataManager;

    @Mock
    private Event event;

    @Mock
    private EventService eventService;

    @Mock
    private List<Event> events;

    @Mock
    private List<Event> expiredEvents;

    @Mock
    private CalendarService calendarService;

//    @Test
//    public void shouldInit() {
//        eventService.init();
//
//        verify(eventService).setDataManager(any(GenericDataManager.class));
//    }
//
//    @Test
//    public void shouldCreateEvent() {
//        eventService.setDataManager(genericDataManager);
//
//        eventService.createEvent(event);
//
//        verify(genericDataManager).save(event);
//    }
//
//    @Test
//    public void shouldGetAllEvents() {
//        eventService.setDataManager(genericDataManager);
//
//        eventService.getAllEvents();
//
//        verify(genericDataManager).getAllEvents();
//    }
//
//    @Test
//    public void shouldUpdateEvent() {
//        eventService.setDataManager(genericDataManager);
//
//        eventService.updateEvent(event);
//
//        verify(genericDataManager).update(event);
//    }
//
//    @Test
//    public void shouldRemoveEvent() {
//        eventService.setDataManager(genericDataManager);
//
//        eventService.removeEvent(event);
//
//        verify(genericDataManager).remove(event);
//    }

    @Test
    public void shouldFilterExpiredEvents() {
        when(eventService.getDataManager()).thenReturn(fileDataManager);
        when(fileDataManager.getAllEventsList()).thenReturn(events);
        when(eventService.getCalendarService()).thenReturn(calendarService);
        when(calendarService.getExpiredEvents(events)).thenReturn(expiredEvents);
        doCallRealMethod().when(eventService).filterExpiredEvents();

        eventService.filterExpiredEvents();

        verify(fileDataManager).remove(expiredEvents);
    }

}
