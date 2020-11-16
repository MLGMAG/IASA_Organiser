package ua.kpi.iasa.IASA_Organiser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.service.EventService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @Mock
    private GenericDataManager genericDataManager;

    @Mock
    private Event event;

    @Spy
    private EventService eventService;

    @Test
    public void shouldInit() {
        eventService.init();

        verify(eventService).setDataManager(any(GenericDataManager.class));
    }

    @Test
    public void shouldCreateEvent() {
        eventService.setDataManager(genericDataManager);

        eventService.createEvent(event);

        verify(genericDataManager).save(event);
    }

    @Test
    public void shouldGetAllEvents() {
        eventService.setDataManager(genericDataManager);

        eventService.getAllEvents();

        verify(genericDataManager).getAllEvents();
    }

    @Test
    public void shouldUpdateEvent() {
        eventService.setDataManager(genericDataManager);

        eventService.updateEvent(event);

        verify(genericDataManager).update(event);
    }

    @Test
    public void shouldRemoveEvent() {
        eventService.setDataManager(genericDataManager);

        eventService.removeEvent(event);

        verify(genericDataManager).remove(event);
    }

}
