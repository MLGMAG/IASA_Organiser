package ua.kpi.iasa.IASA_Organiser.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilityTest {

    @Mock
    private List<Event> eventList;

    @Mock
    private File file;

    @Mock
    private Event event;

    @Mock
    private ObjectOutputStream objectOutputStream;

    @Mock
    private ObjectInputStream objectInputStream;

    @Mock
    private MockedStatic<FileUtility> fileUtilityMocked;

    @Test
    public void shouldParseData() {
        fileUtilityMocked.when(() -> FileUtility.getObjectInputStream(file)).thenReturn(objectInputStream);
        fileUtilityMocked.when(() -> FileUtility.readObjectFromObjectInputStream(objectInputStream)).thenReturn(event, event, null);
        fileUtilityMocked.when(() -> FileUtility.parseData(file, eventList)).thenCallRealMethod();

        FileUtility.parseData(file, eventList);

        verify(eventList, times(2)).add(event);
    }

    @Test
    public void shouldSaveListToFile() {
        List<Event> testList = asList(event, event, event);
        fileUtilityMocked.when(() -> FileUtility.getObjectOutputStream(file)).thenReturn(objectOutputStream);
        fileUtilityMocked.when(() -> FileUtility.saveListToFile(file, testList)).thenCallRealMethod();

        List<Event> actual = FileUtility.saveListToFile(file, testList);

        fileUtilityMocked.verify(times(3), () -> FileUtility.writeObjectToFile(objectOutputStream, event));
        assertEquals(testList, actual);
    }

    @Test
    public void shouldNotSaveListToFile() {
        List<Event> testList = asList(event, event, event);
        fileUtilityMocked.when(() -> FileUtility.getObjectOutputStream(file)).thenReturn(objectOutputStream);
        fileUtilityMocked.when(() -> FileUtility.writeObjectToFile(objectOutputStream, event)).thenThrow(IOException.class);
        fileUtilityMocked.when(() -> FileUtility.saveListToFile(file, testList)).thenCallRealMethod();

        List<Event> actual = FileUtility.saveListToFile(file, testList);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldInitFile() throws IOException {
        when(file.createNewFile()).thenReturn(true);
        fileUtilityMocked.when(() -> FileUtility.initFile(file)).thenCallRealMethod();

        try {
            FileUtility.initFile(file);
        } catch (IOException e) {
            fail();
        }
    }

    @Test(expected = IOException.class)
    public void shouldNotInitFileOnException() throws IOException {
        when(file.createNewFile()).thenReturn(false);
        fileUtilityMocked.when(() -> FileUtility.initFile(file)).thenCallRealMethod();

        FileUtility.initFile(file);
    }

    @Test
    public void shouldGetNewFile() {
        String test = "testDir";
        fileUtilityMocked.when(() -> FileUtility.getNewFile(test)).thenCallRealMethod();

        File newFile = FileUtility.getNewFile(test);

        assertEquals(test, newFile.getPath());

    }
}
