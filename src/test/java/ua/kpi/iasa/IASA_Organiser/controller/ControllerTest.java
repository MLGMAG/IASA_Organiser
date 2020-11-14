package ua.kpi.iasa.IASA_Organiser.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.service.CalendarService;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.view.View;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    private View view;

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

}
