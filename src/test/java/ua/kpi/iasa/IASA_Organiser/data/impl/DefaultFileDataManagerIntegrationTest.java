package ua.kpi.iasa.IASA_Organiser.data.impl;

import org.junit.After;
import org.junit.Before;
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
import ua.kpi.iasa.IASA_Organiser.util.FileUtility;
import ua.kpi.iasa.IASA_Organiser.util.FileUtilityIntegrationTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
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

    private static final Place place1 = new Place("Ukraine", "Kiev", "Metalistiv", 3, "ab");
    private static final LocalDate localDate = LocalDate.of(2020, 9, 29);
    private static final LocalTime time = LocalTime.of(22, 13);
    private static final LocalTime duration = LocalTime.of(2, 30);
    private static final Event event1 = builderDirector.getSingleEvent("Event 1", place1, null, localDate, time, Priority.HIGH, null, duration, null);
    private static final Event event2 = builderDirector.getSingleEvent("Event 2", place1, null, localDate, time, Priority.HIGH, null, duration, null);

    @Before
    public void setUp() throws Exception {
        FileUtilityIntegrationTest.cleanDirectory();
        FileUtility.initFile(FileUtilityIntegrationTest.OUTPUT_FILE);
        defaultFileDataManager.setFile(FileUtilityIntegrationTest.OUTPUT_FILE);
        defaultFileDataManager.setEvents(Collections.emptyList());
    }

    @After
    public void tearDown() {
        FileUtilityIntegrationTest.cleanDirectory();
    }

    @Test
    public void shouldSaveAndCompareEvents() {
        defaultFileDataManager.save(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        assertTrue(result.contains(event1));
    }

    @Test
    public void shouldSaveTwoObjectsAndCompare() {
        defaultFileDataManager.save(event1);
        defaultFileDataManager.save(event2);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldAddTwoObjectsAndRemoveOne() {
        defaultFileDataManager.save(event1);
        defaultFileDataManager.save(event2);
        defaultFileDataManager.remove(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        assertEquals(1, result.size());
        assertFalse(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldRemoveFromEmptyFile() {
        defaultFileDataManager.remove(event1);
        defaultFileDataManager.remove(event2);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        assertEquals(0, result.size());
    }

    @Test
    public void shouldUpdateItem() {
        defaultFileDataManager.save(event1);
        defaultFileDataManager.save(event2);
        defaultFileDataManager.update(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        assertEquals(2, result.size());
        assertTrue(result.contains(event1));
        assertTrue(result.contains(event2));
    }

    @Test
    public void shouldNotUpdateItemOnEmpty() {
        eventBuilder.setInitValues(event1);
        eventBuilder.setName("Event Updated");

        defaultFileDataManager.update(event1);
        List<Event> result = defaultFileDataManager.getAllEventsList();

        assertEquals(0, result.size());
    }

    @Test
    public void multiInit() {
        defaultFileDataManager.save(event1);

        defaultFileDataManager.setFile(FileUtilityIntegrationTest.OUTPUT_FILE);
        defaultFileDataManager.setEvents(new LinkedList<>());
        defaultFileDataManager.initStorage();

        List<Event> allEventsList = defaultFileDataManager.getAllEventsList();

        FileUtilityIntegrationTest.cleanDirectory();
        assertTrue(allEventsList.contains(event1));
    }
}
