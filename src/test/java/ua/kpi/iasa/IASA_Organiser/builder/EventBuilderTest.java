package ua.kpi.iasa.IASA_Organiser.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventBuilderTest {

    @Mock
    private Event event;

    @Mock
    private Place place;

    @Mock
    private List<Human> humanList;

    @Mock
    private List<Tag> tagList;

    @Mock
    private List<Link> linkList;

    @Spy
    private EventBuilder eventBuilder;

    private final UUID uuid = UUID.randomUUID();
    private static final String TEST_STRING = "test";
    private static final boolean expired = true;
    private static final boolean single = true;
    private static final boolean periodic = true;
    private static final boolean deadline = true;
    private static final boolean completable = true;
    private final LocalTime localTime = LocalTime.MAX;
    private final LocalDate localDate = LocalDate.MAX;
    private final Priority priority = Priority.HIGH;
    private final LocalTime duration = LocalTime.MAX;

    @Test
    public void shouldSetInitValues() {
        when(event.getId()).thenReturn(uuid);
        when(event.getName()).thenReturn(TEST_STRING);
        when(event.getPlace()).thenReturn(place);
        when(event.getInvited()).thenReturn(humanList);
        when(event.getDate()).thenReturn(localDate);
        when(event.getTime()).thenReturn(localTime);
        when(event.getPriority()).thenReturn(priority);
        when(event.getTags()).thenReturn(tagList);
        when(event.getDuration()).thenReturn(localTime);
        when(event.getDuration()).thenReturn(duration);
        when(event.getLinks()).thenReturn(linkList);
        when(event.isExpired()).thenReturn(expired);
        when(event.isSingle()).thenReturn(single);
        when(event.isPeriodic()).thenReturn(periodic);
        when(event.isDeadline()).thenReturn(deadline);
        when(event.isCompletable()).thenReturn(completable);

        eventBuilder.setInitValues(event);

        verify(eventBuilder).setId(uuid);
        verify(eventBuilder).setName(TEST_STRING);
        verify(eventBuilder).setPlace(place);
        verify(eventBuilder).setInvited(humanList);
        verify(eventBuilder).setDate(localDate);
        verify(eventBuilder).setTime(localTime);
        verify(eventBuilder).setPriority(priority);
        verify(eventBuilder).setTags(tagList);
        verify(eventBuilder).setDuration(duration);
        verify(eventBuilder).setLinks(linkList);
        verify(eventBuilder).setExpired(expired);
        verify(eventBuilder).setSingle(single);
        verify(eventBuilder).setPeriodic(periodic);
        verify(eventBuilder).setDeadline(deadline);
        verify(eventBuilder).setCompletable(completable);
    }
}
