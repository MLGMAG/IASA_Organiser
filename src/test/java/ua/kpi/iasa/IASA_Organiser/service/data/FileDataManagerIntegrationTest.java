package ua.kpi.iasa.IASA_Organiser.service.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FileDataManagerIntegrationTest {

    @Spy
    private FileDataManager fileDataManager;

    private static final String FILE_PATH = "/home/mlgmag/IdeaProjects/IASA_Organiser/src/main/resources/data/test_output.txt";
    private static final File OUTPUT_FILE = new File(FILE_PATH);

    private static final Place place1 = new Place("Ukraine", "Kiev", "Metalistiv", 3, '-');
    private static final LocalDate localDate = LocalDate.of(2020, 9, 29);
    private static final LocalTime time = LocalTime.of(22, 13);
    private static final LocalTime duration = LocalTime.of(2, 30);
    private static final Event event1 = new Event("Event 1", place1, localDate, time, Priority.HIGH, duration);
    private static final Event event2 = new Event("Event 2", place1, localDate, time, Priority.HIGH, duration);

    @Test
    public void shouldCreateFile() throws IOException {
        cleanDirectory();
        fileDataManager.initFile(OUTPUT_FILE);

        assertTrue(OUTPUT_FILE.exists());
        cleanDirectory();
    }

    @Test
    public void shouldSaveAndCompareEvents() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);
        fileDataManager.save(event1);

        List<Event> result = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertTrue(result.contains(event1));
    }

    @Test
    public void shouldSaveTwoObjectsAndCompare() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);

        fileDataManager.save(event1);
        fileDataManager.save(event2);
        List<Event> result = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldAddTwoObjectsAndRemoveOne() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);

        fileDataManager.save(event1);
        fileDataManager.save(event2);
        fileDataManager.remove(event1);
        List<Event> result = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(1, result.size());
        assertFalse(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldRemoveFromEmptyFile() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);

        fileDataManager.remove(event1);
        fileDataManager.remove(event2);
        List<Event> result = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(0, result.size());
    }

    @Test
    public void shouldUpdateItem() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);
        event1.setId(UUID.randomUUID());
        event2.setId(UUID.randomUUID());

        fileDataManager.save(event1);
        fileDataManager.save(event2);
        event1.setName("Event Updated");
        fileDataManager.update(event1);
        List<Event> result = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldNotUpdateItemOnEmpty() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);

        event1.setName("Event Updated");
        fileDataManager.update(event1);
        List<Event> result = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(0, result.size());
    }

    @Test
    public void multiInit() {
        cleanDirectory();
        fileDataManager.init(FILE_PATH);
        fileDataManager.save(event1);

        fileDataManager.init(FILE_PATH);
        List<Event> allEventsList = fileDataManager.getAllEventsList();

        cleanDirectory();
        assertTrue(allEventsList.contains(event1));
    }

    private void cleanDirectory() {
        if (!OUTPUT_FILE.delete()) {
            System.out.println("Can't delete file: " + FILE_PATH);
        }
    }
}
