package ua.kpi.iasa.IASA_Organiser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.data.FileDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

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

    @Test
    public void shouldCreateEvent() {
        when(eventService.getDataManager()).thenReturn(fileDataManager);
        doCallRealMethod().when(eventService).createEvent(event);

        eventService.createEvent(event);

        verify(fileDataManager).save(event);
    }

    @Test
    public void shouldGetAllEvents() {
        when(eventService.getDataManager()).thenReturn(fileDataManager);
        doCallRealMethod().when(eventService).getAllEvents();

        eventService.getAllEvents();

        verify(fileDataManager).getAllEvents();
    }

    @Test
    public void shouldGetAllEventsList() {
        when(eventService.getDataManager()).thenReturn(fileDataManager);
        doCallRealMethod().when(eventService).getAllEventsList();

        eventService.getAllEventsList();

        verify(fileDataManager).getAllEventsList();

    }

    @Test
    public void shouldUpdateEvent() {
        when(eventService.getDataManager()).thenReturn(fileDataManager);
        doCallRealMethod().when(eventService).updateEvent(event);

        eventService.updateEvent(event);

        verify(fileDataManager).update(event);
    }

    @Test
    public void shouldRemoveEvent() {
        when(eventService.getDataManager()).thenReturn(fileDataManager);
        doCallRealMethod().when(eventService).removeEvent(event);

        eventService.removeEvent(event);

        verify(fileDataManager).remove(event);
    }

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
