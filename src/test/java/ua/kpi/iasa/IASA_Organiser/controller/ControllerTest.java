package ua.kpi.iasa.IASA_Organiser.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Calendar;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.View;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    private View view;

    @Mock
    private EventService eventService;

    @Mock
    private List<Event> eventList;

    @Mock
    private Calendar calendar;

    @Mock
    private CalendarService calendarService;

    @Mock
    private Event event;

    @Spy
    private final Controller controller = new Controller();

    @Test
    public void shouldInit() {
        controller.init(view);

        verify(controller).setEventService(any(EventService.class));
        verify(controller).setCalendarService(any(CalendarService.class));
        verify(view).configController(controller);
        verify(view).startUp();
    }

    @Test
    public void shouldGetAllEvents() {
        controller.setEventService(eventService);
        Event[] events = new Event[0];
        when(eventService.getAllEvents()).thenReturn(events);

        Event[] actual = controller.getAllEvents();

        assertArrayEquals(events, actual);
    }

    @Test
    public void shouldGetAllEventsList() {
        controller.setEventService(eventService);
        when(eventService.getAllEventsList()).thenReturn(eventList);

        List<Event> actual = controller.getAllEventsList();

        assertEquals(eventList, actual);
    }

    @Test
    public void shouldGetCalendar() {
        controller.setCalendarService(calendarService);
        when(calendarService.getCalendar()).thenReturn(calendar);

        Calendar actual = controller.getCalendar();

        assertEquals(calendar, actual);
    }

    @Test
    public void shouldCreateNewEvent() {
        controller.setEventService(eventService);

        controller.createNewEvent(event);

        verify(eventService).createEvent(event);
    }

    @Test
    public void shouldChangeEvent() {
        controller.setEventService(eventService);

        controller.changeEvent(event);

        verify(eventService).updateEvent(event);
    }

    @Test
    public void shouldRemoveEvent() {
        controller.setEventService(eventService);

        controller.removeEvent(event);

        verify(eventService).removeEvent(event);
    }

}
