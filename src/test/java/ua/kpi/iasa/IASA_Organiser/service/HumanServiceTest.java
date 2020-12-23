package ua.kpi.iasa.IASA_Organiser.service;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.repository.EventRepository;
import ua.kpi.iasa.IASA_Organiser.repository.HumanRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HumanServiceTest {

    @Mock
    private Human human;

    @Mock
    private Event event;

    @Mock
    private HumanRepository humanRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventService eventService;

    @Spy
    @InjectMocks
    private HumanService humanService;

    @Test
    public void shouldGetAllHumans() {
        humanService.getAllHumans();

        verify(humanRepository).findAll();
        verify(humanService).sortHumansByFirstName(any());
    }

    @Test
    public void shouldGetHumanById() {
        UUID uuid = UUID.randomUUID();
        when(humanRepository.findById(uuid)).thenReturn(Optional.of(human));

        Human result = humanService.getHumanById(uuid);

        verify(human).setEvents(Sets.newHashSet());
        assertEquals(human, result);
    }

    @Test
    public void shouldNotGetHumanById() {
        UUID uuid = UUID.randomUUID();
        when(humanRepository.findById(uuid)).thenReturn(Optional.empty());

        Human result = humanService.getHumanById(uuid);

        Assert.assertNull(result);
    }

    @Test
    public void shouldGetHumanAndEventsByHumanId() {
        UUID uuid = UUID.randomUUID();
        when(humanRepository.findHumanAndEvents(uuid)).thenReturn(Optional.of(human));

        Human result = humanService.getHumanAndEventsByHumanId(uuid);

        assertEquals(human, result);
    }

    @Test
    public void shouldNotGetHumanAndEventsByHumanId() {
        UUID uuid = UUID.randomUUID();
        when(humanRepository.findHumanAndEvents(uuid)).thenReturn(Optional.empty());

        humanService.getHumanAndEventsByHumanId(uuid);

        verify(humanService).getHumanById(uuid);
    }

    @Test
    public void shouldCreateHuman() {
        humanService.createHuman(human);

        verify(humanRepository).save(human);
    }

    @Test
    public void shouldDeleteById() {
        UUID uuid = UUID.randomUUID();

        humanService.deleteById(uuid);

        verify(humanRepository).deleteById(uuid);
    }

    @Test
    public void shouldDeleteListHuman() {
        UUID uuid = UUID.randomUUID();
        when(human.getId()).thenReturn(uuid);
        List<Human> input = List.of(human, human, human);

        humanService.deleteListHuman(input);

        verify(humanService, times(3)).deleteById(uuid);
    }

    @Test
    public void shouldChangeEmailById() {
        UUID uuid = UUID.randomUUID();
        String testMail = "123";
        when(humanRepository.findById(uuid)).thenReturn(Optional.of(human));

        humanService.changeEmailById(uuid, testMail);

        verify(human).setEmail(testMail);
        verify(humanRepository).save(human);
    }

    @Test
    public void shouldUpdateHuman() {
        UUID uuid = UUID.randomUUID();

        humanService.updateHuman(uuid, human);

        verify(human).setId(uuid);
        verify(humanRepository).save(human);
    }

    @Test
    public void shouldCreateListHuman() {
        List<Human> input = List.of(human, human, human);

        humanService.createListHuman(input);

        verify(humanRepository).saveAll(input);
    }

    @Test
    public void shouldJoinEvent() {
        UUID humanId = UUID.randomUUID();
        UUID eventId = UUID.randomUUID();
        when(humanRepository.findById(humanId)).thenReturn(Optional.of(human));
        doNothing().when(humanService).addHumanToEvent(eventId, human);

        humanService.joinEvent(humanId, eventId);

        verify(humanService).addHumanToEvent(eventId, human);
    }

    @Test
    public void shouldAddHumanToEvent() {
        UUID eventId = UUID.randomUUID();
        Set<Human> invited = spy(Sets.newHashSet(human));
        when(eventService.getEventAndHumansByEventId(eventId)).thenReturn(event);
        when(event.getInvited()).thenReturn(invited);

        humanService.addHumanToEvent(eventId, human);

        verify(invited).add(human);
        verify(eventRepository).save(event);
    }

    @Test
    public void shouldLeaveEvent() {
        UUID humanId = UUID.randomUUID();
        UUID eventId = UUID.randomUUID();
        when(humanRepository.findById(humanId)).thenReturn(Optional.of(human));
        doNothing().when(humanService).removeEventFromHumanAndSave(human, eventId);

        humanService.leaveEvent(humanId, eventId);

        verify(humanService).removeEventFromHumanAndSave(human, eventId);
    }

    @Test
    public void shouldRemoveEventFromHumanAndSave() {
        UUID eventId = UUID.randomUUID();
        HashSet<Human> invited = spy(Sets.newHashSet(human, human, human));
        when(eventService.getEventAndHumansByEventId(eventId)).thenReturn(event);
        when(event.getInvited()).thenReturn(invited);

        humanService.removeEventFromHumanAndSave(human, eventId);

        verify(invited).remove(human);
        verify(eventRepository).save(event);
    }
}
