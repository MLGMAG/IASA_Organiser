package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.util.FileUtility;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
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
        fileUtilityMock.when(() -> FileUtility.saveListToFile(file, eventList)).thenReturn(newEventList);
        doCallRealMethod().when(defaultFileDataManager).saveAll(eventList);

        defaultFileDataManager.saveAll(eventList);

        verify(defaultFileDataManager).setEvents(newEventList);
    }

    @Test
    public void shouldUpdate() {
        Event updatedEvent = mock(Event.class);
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        List<Event> testData = asList(event1, event2);
        when(defaultFileDataManager.getEvents()).thenReturn(testData);
        when(updatedEvent.getId()).thenReturn(uuid1);
        when(event1.getId()).thenReturn(uuid2);
        when(event2.getId()).thenReturn(uuid1);
        doCallRealMethod().when(defaultFileDataManager).update(updatedEvent);

        defaultFileDataManager.update(updatedEvent);

        verify(defaultFileDataManager).saveAll(testData);
        assertEquals(updatedEvent, testData.get(1));
    }

    @Test
    public void shouldNotUpdate() {
        Event updatedEvent = mock(Event.class);
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        List<Event> testData = asList(event1, event2);
        when(defaultFileDataManager.getEvents()).thenReturn(testData);
        when(updatedEvent.getId()).thenReturn(uuid1);
        when(event1.getId()).thenReturn(uuid2);
        when(event2.getId()).thenReturn(uuid3);
        doCallRealMethod().when(defaultFileDataManager).update(updatedEvent);

        defaultFileDataManager.update(updatedEvent);

        verify(defaultFileDataManager, never()).saveAll(testData);
    }

}
