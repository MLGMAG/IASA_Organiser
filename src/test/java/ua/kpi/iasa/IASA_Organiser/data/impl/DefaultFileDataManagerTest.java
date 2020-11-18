package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.util.FileUtility;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFileDataManagerTest {

    @Mock
    private Event event;

    @Mock
    private ObjectInputStream objectInputStream;

    @Mock
    private ObjectOutputStream objectOutputStream;

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

    private static final String FILE_PATH = "test_path";

//    @Test
//    public void shouldInitNotExistingFile() {
//        fileUtilityMock.when(() -> FileUtility.getNewFile(FILE_PATH)).thenReturn(file);
//        when(file.exists()).thenReturn(false);
//        doCallRealMethod().when(defaultFileDataManager).initStorage(FILE_PATH);
//
//        defaultFileDataManager.initStorage(FILE_PATH);
//
//        fileUtilityMock.verify(() -> {
//            FileUtility.initFile(file);
//        });
//    }

//    @Test
//    public void shouldInitOnExistingFile() {
//        fileUtilityMock.when(() -> FileUtility.getNewFile(FILE_PATH)).thenReturn(file);
//        when(file.exists()).thenReturn(true);
//        doReturn(eventList).when(defaultFileDataManager).createNewEventList();
//        doCallRealMethod().when(defaultFileDataManager).initStorage(FILE_PATH);
//
//        defaultFileDataManager.initStorage(FILE_PATH);
//
//        fileUtilityMock.verify((() -> FileUtility.getNewFile(FILE_PATH)));
//        fileUtilityMock.verify(() -> {
//            FileUtility.parseData(file, eventList);
//        });
//    }

//    @Test
//    public void shouldSave() {
//        setUpEvents(eventList);
//        doReturn(newEventList).when(defaultFileDataManager).createNewEventList();
//        doNothing().when(defaultFileDataManager).saveAll(newEventList);
//
//        defaultFileDataManager.save(event);
//
//        verify(newEventList).addAll(eventList);
//        verify(newEventList).add(event);
//    }

//    @Test
//    public void shouldGetAllEventsList() throws IOException {
//        setUpMocks(eventList);
//
//        List<Event> actual = defaultFileDataManager.getAllEventsList();
//
//        assertEquals(eventList, actual);
//    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldGetAllEvents() {
        defaultFileDataManager.getAllEvents();
    }

    @Test
    public void shouldNotSaveAllOnRemove() {
        when(defaultFileDataManager.getAllEventsList()).thenReturn(eventList);
        when(eventList.remove(event)).thenReturn(false);

        defaultFileDataManager.remove(event);

        verify(defaultFileDataManager, never()).saveAll(eventList);
    }

//    @Test
//    public void shouldSaveAll() throws IOException {
//        List<Event> testData = asList(event, event, event);
//        setUpMocks(eventList);
//        doReturn(objectOutputStream).when(defaultFileDataManager).getObjectOutputStream(file);
//        doNothing().when(defaultFileDataManager).writeObjectToFile(objectOutputStream, event);
//
//        defaultFileDataManager.saveAll(testData);
//
//        verify(defaultFileDataManager, times(3)).writeObjectToFile(objectOutputStream, event);
//        assertEquals(defaultFileDataManager.getAllEventsList(), testData);
//    }

//    @Test
//    public void shouldUpdate() {
//        Event updatedEvent = mock(Event.class);
//        Event event1 = mock(Event.class);
//        Event event2 = mock(Event.class);
//        UUID uuid1 = UUID.randomUUID();
//        UUID uuid2 = UUID.randomUUID();
//        List<Event> testData = asList(event1, event2);
//        when(defaultFileDataManager.getAllEventsList()).thenReturn(testData);
//        when(updatedEvent.getId()).thenReturn(uuid1);
//        when(event1.getId()).thenReturn(uuid2);
//        when(event2.getId()).thenReturn(uuid1);
//        doNothing().when(defaultFileDataManager).saveAll(testData);
//
//        defaultFileDataManager.update(updatedEvent);
//
//        assertEquals(updatedEvent, testData.get(1));
//    }

    @Test
    public void shouldNotUpdate() {
        Event updatedEvent = mock(Event.class);
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        List<Event> testData = asList(event1, event2);
        when(defaultFileDataManager.getAllEventsList()).thenReturn(testData);
        when(updatedEvent.getId()).thenReturn(uuid1);
        when(event1.getId()).thenReturn(uuid2);
        when(event2.getId()).thenReturn(uuid3);

        defaultFileDataManager.update(updatedEvent);

        verify(defaultFileDataManager, never()).saveAll(testData);
    }

//    private void setUpEvents(List<Event> events) {
//        fileUtilityMock.when(() -> FileUtility.getNewFile(FILE_PATH)).thenReturn(file);
//        doReturn(events).when(defaultFileDataManager).createNewEventList();
//        when(file.exists()).thenReturn(false);
//        defaultFileDataManager.initStorage(FILE_PATH);
//    }
}
