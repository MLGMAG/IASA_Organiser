package ua.kpi.iasa.IASA_Organiser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.iasa.IASA_Organiser.model.*;
import ua.kpi.iasa.IASA_Organiser.service.EventService;
import ua.kpi.iasa.IASA_Organiser.service.data.impl.DefaultArrayDataManager;

import java.time.LocalDate;
import java.time.LocalTime;

public class GeneralTests {
    Place place1 = new Place("Ukraine", "Kiev", "Metalistiv", 3, '-');
    Human human1 = new Human("Mahomed", "Akhmedov", "1234567890", "maga@makeworldbest");
    Human human2 = new Human("Oleksiy", "Kosiuk", "0987654321", "alorithmist@iasa");
    Human[] invited = {human1, human2};
    LocalDate localDate = LocalDate.of(2020, 9, 29);
    LocalTime time = LocalTime.of(22, 13);
    Tag tag1 = new Tag("First tag");
    Tag tag2 = new Tag("Second tag");
    Tag[] tags = {tag1, tag2};
    LocalTime duration = LocalTime.of(2, 30);
    Link link1 = new Link("blblblblbbl");
    Link link2 = new Link("ghghghhghghgh");
    Link[] links = {link1, link2};
    Event event1 = new Event("Event 1", place1, localDate, time, Priority.HIGH, duration);
    Event event2 = new Event("Event 2", place1, localDate, time, Priority.HIGH, duration);

    EventService eventService;

    @Before
    public void setUp() {
        eventService = EventService.getInstance();
    }

    @Test
    public void test1() {

        eventService.createEvent(event1);
        eventService.createEvent(event2);
        Event[] events = eventService.getAllEvents();
        Assert.assertEquals(events[0].getName(), event1.getName());
        Assert.assertEquals(events[1].getName(), event2.getName());
    }

    @Test(expected = ArrayStoreException.class)
    public void test2() {
        for (int i = 0; i < DefaultArrayDataManager.getInstance().getMaxSizeOfData() + 1; i++) {
            eventService.createEvent(event1);
        }
    }
}
