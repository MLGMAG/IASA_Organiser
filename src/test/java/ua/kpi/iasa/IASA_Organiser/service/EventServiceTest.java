package ua.kpi.iasa.IASA_Organiser.service;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private Event event;

    @Mock
    private CalendarService calendarService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private HumanRepository humanRepository;

    @Spy
    @InjectMocks
    private EventService eventService;

    @Test
    public void shouldCreateEvent() {
        doNothing().when(eventService).validateEventPlace(event);

        eventService.createEvent(event);

        verify(eventService).validateEventPlace(event);
        verify(eventRepository).save(event);
    }

    @Test
    public void shouldSetNullOnValidation() {
        Place place = new Place();
        place.setCountry("");
        Event input = new Event();
        input.setPlace(place);

        eventService.validateEventPlace(input);

        assertNull(input.getPlace());
    }

    @Test
    public void shouldNotSetNullOnValidation() {
        Place place = new Place();
        place.setCountry("Ukraine");
        Event input = new Event();
        input.setPlace(place);

        eventService.validateEventPlace(input);

        assertNotNull(input.getPlace());
    }

    @Test
    public void shouldGetAllEvents() {
        eventService.getAllEvents();

        verify(eventRepository).findAll();
        verify(eventService).sortEventsByPriority(any());
    }

    @Test
    public void shouldUpdateEvent() {
        UUID uuid = UUID.randomUUID();

        eventService.updateEvent(uuid, event);

        verify(event).setId(uuid);
        verify(eventRepository).save(event);
    }

    @Test
    public void shouldGetEventById() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.findById(uuid)).thenReturn(Optional.of(event));

        Event eventById = eventService.getEventById(uuid);

        verify(event).setInvited(Sets.newHashSet());
        assertEquals(event, eventById);
    }

    @Test
    public void shouldNotGetEventById() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.findById(uuid)).thenReturn(Optional.empty());

        Event actual = eventService.getEventById(uuid);

        verify(event, never()).setInvited(Sets.newHashSet());
        assertNull(actual);
    }

    @Test
    public void shouldGetEventAndHumansByEventId() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.findEventAndHumans(uuid)).thenReturn(Optional.of(event));

        Event actual = eventService.getEventAndHumansByEventId(uuid);

        assertEquals(event, actual);
    }

    @Test
    public void shouldNotGetEventAndHumansByEventId() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.findEventAndHumans(uuid)).thenReturn(Optional.empty());

        eventService.getEventAndHumansByEventId(uuid);

        verify(eventService).getEventById(uuid);
    }

    @Test
    public void shouldCreateListEvents() {
        List<Event> input = List.of(event, event, event);

        eventService.createListEvents(input);

        verify(eventRepository, times(3)).save(event);
    }

    @Test
    public void shouldDeleteListEvent() {
        UUID uuid = UUID.randomUUID();
        when(event.getId()).thenReturn(uuid);
        List<Event> input = List.of(event, event, event);

        eventService.deleteListEvent(input);

        verify(event, times(3)).getId();
        verify(eventRepository, times(3)).deleteById(uuid);
    }

    @Test
    public void shouldSortEventsByPriority() {
        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        event1.setPriority(Priority.LOW);
        event2.setPriority(Priority.MEDIUM);
        event3.setPriority(Priority.HIGH);
        List<Event> input = List.of(event1, event2, event3);

        List<Event> result = eventService.sortEventsByPriority(input);

        assertEquals(event3, result.get(0));
        assertEquals(event2, result.get(1));
        assertEquals(event1, result.get(2));
    }

    @Test
    public void shouldDeleteById() {
        UUID uuid = UUID.randomUUID();
        doReturn(event).when(eventService).getEventAndHumansByEventId(uuid);

        eventService.deleteById(uuid);

        verify(eventService).cleanEventFromHumans(event);
        verify(eventService).deleteById(uuid);
    }

    @Test
    public void shouldRemoveEventFromHumanAndSaveHuman() {
        Human human = new Human();
        HashSet<Event> events = spy(Sets.newHashSet(event));
        human.setEvents(events);

        eventService.removeEventFromHumanAndSaveHuman(event, human);

        verify(events).remove(event);
        verify(humanRepository).save(human);
    }

    @Test
    public void shouldRemoveEvent() {
        eventService.removeEvent(event);

        verify(eventRepository).delete(event);
    }

    @Test
    public void shouldFilterExpiredEvents() {
        List<Event> currentEvents = List.of(event);
        List<Event> expiredEvents = List.of(event);
        when(eventRepository.findAll()).thenReturn(currentEvents);
        when(calendarService.getExpiredEvents(currentEvents)).thenReturn(expiredEvents);

        eventService.filterExpiredEvents();

        verify(eventService).deleteListEvent(expiredEvents);
    }

}
