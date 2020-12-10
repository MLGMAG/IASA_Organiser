//package ua.kpi.iasa.IASA_Organiser.controller;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import ua.kpi.iasa.IASA_Organiser.model.Event;
//import ua.kpi.iasa.IASA_Organiser.model.Priority;
//import ua.kpi.iasa.IASA_Organiser.model.Tag;
//import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
//import ua.kpi.iasa.IASA_Organiser.service.EventService;
//import ua.kpi.iasa.IASA_Organiser.view.View;
//
//import java.time.LocalDate;
//
//import static org.mockito.Mockito.doCallRealMethod;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ControllerTest {
//
//    @Mock
//    private Controller controller;
//
//    @Mock
//    private EventService eventService;
//
//    @Mock
//    private Event event;
//
//    @Mock
//    private View view;
//
//    @Mock
//    private CalendarService calendarService;
//
//    @Mock
//    private Tag tag;
//
//    @Mock
//    private LocalDate date;
//
//    @Mock
//    private Priority priority;
//
//    @Test
//    public void shouldInit() {
//        doCallRealMethod().when(controller).init(view);
//
//        controller.init(view);
//
//        verify(view).configController(controller);
//        verify(view).startUp();
//    }
//
//    @Test
//    public void shouldGetAllEventsList() {
//        when(controller.getEventService()).thenReturn(eventService);
//        when(controller.getAllEventsList()).thenCallRealMethod();
//
//        controller.getAllEventsList();
//
//        verify(eventService).getAllEventsList();
//    }
//
//    @Test
//    public void shouldCreateNewEvent() {
//        when(controller.getEventService()).thenReturn(eventService);
//        doCallRealMethod().when(controller).createNewEvent(event);
//
//        controller.createNewEvent(event);
//
//        verify(eventService).createEvent(event);
//    }
//
//    @Test
//    public void shouldUpdateEvent() {
//        when(controller.getEventService()).thenReturn(eventService);
//        doCallRealMethod().when(controller).updateEvent(event);
//
//        controller.updateEvent(event);
//
//        verify(eventService).updateEvent(event);
//    }
//
//    @Test
//    public void shouldRemoveEvent() {
//        when(controller.getEventService()).thenReturn(eventService);
//        doCallRealMethod().when(controller).removeEvent(event);
//
//        controller.removeEvent(event);
//
//        verify(eventService).removeEvent(event);
//    }
//
//    @Test
//    public void shouldFindEventsByDate() {
//        when(controller.getCalendarService()).thenReturn(calendarService);
//        doCallRealMethod().when(controller).findEventsByDate(date);
//
//        controller.findEventsByDate(date);
//
//        verify(calendarService).findEventsByDate(date);
//    }
//
//    @Test
//    public void shouldFindEventByTag() {
//        when(controller.getCalendarService()).thenReturn(calendarService);
//        doCallRealMethod().when(controller).findEventByTag(tag);
//
//        controller.findEventByTag(tag);
//
//        verify(calendarService).findEventsByTag(tag);
//    }
//
//    @Test
//    public void shouldFindEventByPriority() {
//        when(controller.getCalendarService()).thenReturn(calendarService);
//        doCallRealMethod().when(controller).findEventByPriority(priority);
//
//        controller.findEventByPriority(priority);
//
//        verify(calendarService).findEventsByPriority(priority);
//    }
//
//    @Test
//    public void shouldSortEventsByPriority() {
//        when(controller.getCalendarService()).thenReturn(calendarService);
//        when(controller.sortEventsByPriority()).thenCallRealMethod();
//
//        controller.sortEventsByPriority();
//
//        verify(calendarService).sortEventsByPriority();
//    }
//}
