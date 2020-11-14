package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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

    @Spy
    private DefaultFileDataManager defaultFileDataManager;

    private static final String FILE_PATH = "test_path";

    @Test
    public void shouldInitNotExistingFile() throws IOException {
        doReturn(file).when(defaultFileDataManager).getFile(FILE_PATH);
        when(file.exists()).thenReturn(false);
        doNothing().when(defaultFileDataManager).initFile(file);

        defaultFileDataManager.init(FILE_PATH);

        verify(defaultFileDataManager).getList();
    }

    @Test
    public void shouldInitOnExistingFile() throws IOException, ClassNotFoundException {
        doReturn(file).when(defaultFileDataManager).getFile(FILE_PATH);
        doReturn(eventList).when(defaultFileDataManager).getList();
        when(file.exists()).thenReturn(true, true);
        doReturn(objectInputStream).when(defaultFileDataManager).getObjectInputStream(file);
        when(objectInputStream.available()).thenReturn(0, 0, -1);
        doReturn(event, event).when(defaultFileDataManager).readObjectFromObjectInputStream(objectInputStream);

        defaultFileDataManager.init(FILE_PATH);

        verify(defaultFileDataManager, never()).initFile(file);
        verify(eventList, times(2)).add(event);
    }

    @Test
    public void shouldSave() throws IOException {
        setUpMocks(eventList);
        when(defaultFileDataManager.getList()).thenReturn(newEventList);
        doNothing().when(defaultFileDataManager).saveAll(newEventList);

        defaultFileDataManager.save(event);

        verify(newEventList).addAll(eventList);
        verify(newEventList).add(event);
    }

    @Test
    public void shouldGetAllEventsList() throws IOException {
        setUpMocks(eventList);

        List<Event> actual = defaultFileDataManager.getAllEventsList();

        assertEquals(eventList, actual);
    }

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

    @Test
    public void shouldSaveAll() throws IOException {
        List<Event> testData = asList(event, event, event);
        setUpMocks(eventList);
        doReturn(objectOutputStream).when(defaultFileDataManager).getObjectOutputStream(file);
        doNothing().when(defaultFileDataManager).writeObjectToFile(objectOutputStream, event);

        defaultFileDataManager.saveAll(testData);

        verify(defaultFileDataManager, times(3)).writeObjectToFile(objectOutputStream, event);
        assertEquals(defaultFileDataManager.getAllEventsList(), testData);
    }

    @Test
    public void shouldUpdate() {
        Event updatedEvent = mock(Event.class);
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        List<Event> testData = asList(event1, event2);
        when(defaultFileDataManager.getAllEventsList()).thenReturn(testData);
        when(updatedEvent.getId()).thenReturn(uuid1);
        when(event1.getId()).thenReturn(uuid2);
        when(event2.getId()).thenReturn(uuid1);
        doNothing().when(defaultFileDataManager).saveAll(testData);

        defaultFileDataManager.update(updatedEvent);

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
        when(defaultFileDataManager.getAllEventsList()).thenReturn(testData);
        when(updatedEvent.getId()).thenReturn(uuid1);
        when(event1.getId()).thenReturn(uuid2);
        when(event2.getId()).thenReturn(uuid3);

        defaultFileDataManager.update(updatedEvent);

        verify(defaultFileDataManager, never()).saveAll(testData);
    }

    private void setUpMocks(List<Event> events) throws IOException {
        doReturn(file).when(defaultFileDataManager).getFile(FILE_PATH);
        doReturn(events).when(defaultFileDataManager).getList();
        when(file.exists()).thenReturn(false);
        doNothing().when(defaultFileDataManager).initFile(file);

        defaultFileDataManager.init(FILE_PATH);
    }
}
