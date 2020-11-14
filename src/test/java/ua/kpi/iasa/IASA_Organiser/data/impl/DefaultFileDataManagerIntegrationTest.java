package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kpi.iasa.IASA_Organiser.builder.Builder;
import ua.kpi.iasa.IASA_Organiser.builder.BuilderDirector;
import ua.kpi.iasa.IASA_Organiser.builder.EventBuilder;
import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFileDataManagerIntegrationTest {

    @Spy
    private DefaultFileDataManager defaultFileDataManager;

    private static final BuilderDirector builderDirector = new BuilderDirector();
    private static final Builder eventBuilder = new EventBuilder();

    private static final String FILE_PATH = "/home/mlgmag/IdeaProjects/IASA_Organiser/src/main/resources/data/test_output.txt";
    private static final File OUTPUT_FILE = new File(FILE_PATH);

    private static final Place place1 = new Place("Ukraine", "Kiev", "Metalistiv", 3, "ab");
    private static final LocalDate localDate = LocalDate.of(2020, 9, 29);
    private static final LocalTime time = LocalTime.of(22, 13);
    private static final LocalTime duration = LocalTime.of(2, 30);
    private static final Event event1 = builderDirector.getSingleEvent("Event 1", place1, null, localDate, time, Priority.HIGH, null, duration, null);
    private static final Event event2 = builderDirector.getSingleEvent("Event 2", place1, null, localDate, time, Priority.HIGH, null, duration, null);

    @Test
    public void shouldCreateFile() throws IOException {
        cleanDirectory();
        defaultFileDataManager.initFile(OUTPUT_FILE);

        assertTrue(OUTPUT_FILE.exists());
        cleanDirectory();
    }

    @Test
    public void shouldSaveAndCompareEvents() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);
        defaultFileDataManager.save(event1);

        List<Event> result = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        assertTrue(result.contains(event1));
    }

    @Test
    public void shouldSaveTwoObjectsAndCompare() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);

        defaultFileDataManager.save(event1);
        defaultFileDataManager.save(event2);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        System.out.println(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldAddTwoObjectsAndRemoveOne() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);

        defaultFileDataManager.save(event1);
        defaultFileDataManager.save(event2);
        defaultFileDataManager.remove(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(1, result.size());
        assertFalse(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldRemoveFromEmptyFile() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);

        defaultFileDataManager.remove(event1);
        defaultFileDataManager.remove(event2);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(0, result.size());
    }

    @Test
    public void shouldUpdateItem() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);

        defaultFileDataManager.save(event1);
        defaultFileDataManager.save(event2);

        defaultFileDataManager.update(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldNotUpdateItemOnEmpty() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);

        eventBuilder.setInitValues(event1);
        eventBuilder.setName("Event Updated");
        defaultFileDataManager.update(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        assertEquals(0, result.size());
    }

    @Test
    public void multiInit() {
        cleanDirectory();
        defaultFileDataManager.init(FILE_PATH);
        defaultFileDataManager.save(event1);

        defaultFileDataManager.init(FILE_PATH);
        List<Event> allEventsList = defaultFileDataManager.getAllEventsList();

        cleanDirectory();
        assertTrue(allEventsList.contains(event1));
    }

    private void cleanDirectory() {
        if (!OUTPUT_FILE.delete()) {
            System.out.println("Can't delete file: " + FILE_PATH);
        }
    }
}
