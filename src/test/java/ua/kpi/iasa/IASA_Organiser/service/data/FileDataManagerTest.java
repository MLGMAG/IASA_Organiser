package ua.kpi.iasa.IASA_Organiser.service.data;

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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileDataManagerTest {

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

    @Spy
    private FileDataManager fileDataManager;

    private static final String FILE_PATH = "test_path";

    @Test
    public void shouldInitNotExistingFile() throws IOException {
        doReturn(file).when(fileDataManager).getFile(FILE_PATH);
        when(file.exists()).thenReturn(false);
        doNothing().when(fileDataManager).initFile(file);

        fileDataManager.init(FILE_PATH);

        assertFalse(fileDataManager.isDataChanged());
    }

    @Test
    public void shouldInitOnExistingFile() throws IOException, ClassNotFoundException {
        doReturn(file).when(fileDataManager).getFile(FILE_PATH);
        doReturn(eventList).when(fileDataManager).getList();
        when(file.exists()).thenReturn(true, true);
        doReturn(objectInputStream).when(fileDataManager).getObjectInputStream(file);
        when(objectInputStream.available()).thenReturn(0, 0, -1);
        doReturn(event, event).when(fileDataManager).readObjectFromObjectInputStream(objectInputStream);

        fileDataManager.init(FILE_PATH);

        verify(fileDataManager, never()).initFile(file);
        verify(eventList, times(2)).add(event);
        assertFalse(fileDataManager.isDataChanged());
    }

    @Test
    public void shouldSave() throws IOException {
        setUpMocks();
        List<Event> testData = new ArrayList<>();
        testData.add(event);
        testData.add(event);
        List<Event> spyList = spy(testData);
        doReturn(file).when(fileDataManager).getFile(FILE_PATH);
        doReturn(spyList).when(fileDataManager).getList();
        when(file.exists()).thenReturn(false);
        doNothing().when(fileDataManager).initFile(file);
        fileDataManager.init(FILE_PATH);
        doReturn(objectInputStream).when(fileDataManager).getObjectInputStream(file);
        doReturn(objectOutputStream).when(fileDataManager).getObjectOutputStream(file);
        doNothing().when(fileDataManager).writeObjectToFile(objectOutputStream, event);

        fileDataManager.save(event);

        verify(spyList).add(event);
        verify(fileDataManager, times(3)).writeObjectToFile(objectOutputStream, event);
    }

    private void setUpMocks() throws IOException {
        doReturn(file).when(fileDataManager).getFile(FILE_PATH);
        doReturn(eventList).when(fileDataManager).getList();
        when(file.exists()).thenReturn(false);
        doNothing().when(fileDataManager).initFile(file);

        fileDataManager.init(FILE_PATH);
    }
}
