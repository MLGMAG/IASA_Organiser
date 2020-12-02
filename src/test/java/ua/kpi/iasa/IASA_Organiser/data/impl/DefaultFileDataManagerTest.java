package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.builder.EventBuilder;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.util.FileUtility;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFileDataManagerTest {

    @Mock
    private Event event;

    @Mock
    private File file;

    @Mock
    private List<Event> eventList;

    @Mock
    private List<Event> newEventList;

    @Mock
    private List<Event> expiredEventList;

    @Mock
    private MockedStatic<FileUtility> fileUtilityMock;

    @Mock
    private DefaultFileDataManager defaultFileDataManager;

    @Test
    public void shouldInitState() {
        fileUtilityMock.when(() -> FileUtility.getNewFile(DefaultFileDataManager.STORAGE_PATH)).thenReturn(file);
        when(defaultFileDataManager.createNewEventList()).thenReturn(eventList);
        doCallRealMethod().when(defaultFileDataManager).initState();

        defaultFileDataManager.initState();

        verify(defaultFileDataManager).setFile(file);
        verify(defaultFileDataManager).setEvents(eventList);
    }

    @Test
    public void shouldInitStorageOnNotExistingFile() {
        when(defaultFileDataManager.getFile()).thenReturn(file);
        when(file.exists()).thenReturn(false);
        doCallRealMethod().when(defaultFileDataManager).initStorage();

        defaultFileDataManager.initStorage();

        fileUtilityMock.verify(() -> FileUtility.initFile(file));
    }

    @Test
    public void shouldInitStorageOnExistingFile() {
        when(defaultFileDataManager.getFile()).thenReturn(file);
        when(file.exists()).thenReturn(true);
        when(defaultFileDataManager.getEvents()).thenReturn(eventList);
        doCallRealMethod().when(defaultFileDataManager).initStorage();

        defaultFileDataManager.initStorage();

        fileUtilityMock.verify(() -> FileUtility.parseData(file, eventList));
    }

//    @Test
//    public void shouldNotInitOnException() {
//        when(defaultFileDataManager.getFile()).thenReturn(file);
//        when(file.exists()).thenReturn(false);
//        fileUtilityMock.when(() -> FileUtility.initFile(file)).thenThrow(IOException.class);
//        doCallRealMethod().when(defaultFileDataManager).initStorage();
//
//        defaultFileDataManager.initStorage();
//    }

    @Test
    public void shouldSave() {
        when(defaultFileDataManager.createNewEventList()).thenReturn(newEventList);
        when(defaultFileDataManager.getEvents()).thenReturn(eventList);
        doNothing().when(defaultFileDataManager).saveAll(newEventList);
        doCallRealMethod().when(defaultFileDataManager).save(event);

        defaultFileDataManager.save(event);

        verify(newEventList).addAll(eventList);
        verify(newEventList).add(event);
    }

    @Test
    public void shouldGetAllEventsList() {
        when(defaultFileDataManager.getEvents()).thenReturn(eventList);
        when(defaultFileDataManager.getAllEventsList()).thenCallRealMethod();

        List<Event> actual = defaultFileDataManager.getAllEventsList();

        assertEquals(eventList, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldGetAllEvents() {
        doCallRealMethod().when(defaultFileDataManager).getAllEvents();

        defaultFileDataManager.getAllEvents();
    }

    @Test
    public void shouldSaveAllOnRemove() {
        when(defaultFileDataManager.getEvents()).thenReturn(eventList);
        when(eventList.remove(event)).thenReturn(true);
        doCallRealMethod().when(defaultFileDataManager).remove(event);

        defaultFileDataManager.remove(event);

        verify(defaultFileDataManager).saveAll(eventList);
    }

    @Test
    public void shouldNotSaveAllOnRemove() {
        when(defaultFileDataManager.getEvents()).thenReturn(eventList);
        when(eventList.remove(event)).thenReturn(false);
        doCallRealMethod().when(defaultFileDataManager).remove(event);

        defaultFileDataManager.remove(event);

        verify(defaultFileDataManager, never()).saveAll(eventList);
    }

    @Test
    public void shouldSaveAll() {
        when(defaultFileDataManager.getFile()).thenReturn(file);
        fileUtilityMock.when(() -> FileUtility.saveListToFile(file, eventList)).thenReturn(Optional.of(newEventList));
        doCallRealMethod().when(defaultFileDataManager).saveAll(eventList);

        defaultFileDataManager.saveAll(eventList);

        verify(defaultFileDataManager).setEvents(newEventList);
    }

    @Test
    public void shouldUpdate() {
        Event updatedEvent = new EventBuilder().setName("update").setId(UUID.randomUUID()).build();
        Event event1 = new EventBuilder().setName("ev1").setId(UUID.randomUUID()).build();
        Event event2 = new EventBuilder().setName("ev2").setId(updatedEvent.getId()).build();
        List<Event> testData = asList(event1, event2);
        when(defaultFileDataManager.getEvents()).thenReturn(testData);
        doCallRealMethod().when(defaultFileDataManager).update(updatedEvent);

        defaultFileDataManager.update(updatedEvent);

        verify(defaultFileDataManager).saveAll(testData);
        assertTrue(testData.contains(updatedEvent));
        assertTrue(testData.stream().anyMatch(event3 -> event3.getName().equals(updatedEvent.getName())));
        assertFalse(testData.stream().anyMatch(event3 -> event3.getName().equals(event2.getName())));
    }

    @Test
    public void shouldNotUpdate() {
        Event updatedEvent = new EventBuilder().setId(UUID.randomUUID()).build();
        Event event1 = new EventBuilder().setId(UUID.randomUUID()).build();
        Event event2 = new EventBuilder().setId(UUID.randomUUID()).build();
        List<Event> testData = asList(event1, event2);
        when(defaultFileDataManager.getEvents()).thenReturn(testData);
        doCallRealMethod().when(defaultFileDataManager).update(updatedEvent);

        defaultFileDataManager.update(updatedEvent);

        verify(defaultFileDataManager, never()).saveAll(testData);
    }

    @Test
    public void shouldRemove() {
        when(defaultFileDataManager.getEvents()).thenReturn(eventList);
        doReturn(newEventList).when(defaultFileDataManager).filterEvents(eventList, expiredEventList);
        doCallRealMethod().when(defaultFileDataManager).remove(expiredEventList);

        defaultFileDataManager.remove(expiredEventList);

        verify(defaultFileDataManager).filterEvents(eventList, expiredEventList);
        verify(defaultFileDataManager).saveAll(newEventList);
    }

    @Test
    public void shouldFilterAll() {
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        Event event3 = mock(Event.class);
        List<Event> currentData = asList(event1, event2, event3);
        List<Event> dataToRemove = asList(event1, event2, event3);
        doCallRealMethod().when(defaultFileDataManager).filterEvents(currentData, dataToRemove);

        List<Event> result = defaultFileDataManager.filterEvents(currentData, dataToRemove);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFilterTwo() {
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        Event event3 = mock(Event.class);
        List<Event> currentData = asList(event1, event2, event3);
        List<Event> dataToRemove = asList(event1, event2);
        doCallRealMethod().when(defaultFileDataManager).filterEvents(currentData, dataToRemove);

        List<Event> result = defaultFileDataManager.filterEvents(currentData, dataToRemove);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertTrue(result.contains(event3));
    }

    @Test
    public void shouldFilterNothingOnEmptyExcludeList() {
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        Event event3 = mock(Event.class);
        List<Event> currentData = asList(event1, event2, event3);
        List<Event> dataToRemove = Collections.emptyList();
        doCallRealMethod().when(defaultFileDataManager).filterEvents(currentData, dataToRemove);

        List<Event> result = defaultFileDataManager.filterEvents(currentData, dataToRemove);

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.containsAll(currentData));
    }

    @Test
    public void shouldFilterNothingOnFullExcludeList() {
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        Event event3 = mock(Event.class);
        Event event4 = mock(Event.class);
        List<Event> currentData = asList(event1, event2, event3);
        List<Event> dataToRemove = Collections.singletonList(event4);
        doCallRealMethod().when(defaultFileDataManager).filterEvents(currentData, dataToRemove);

        List<Event> result = defaultFileDataManager.filterEvents(currentData, dataToRemove);

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.containsAll(currentData));
    }

}
