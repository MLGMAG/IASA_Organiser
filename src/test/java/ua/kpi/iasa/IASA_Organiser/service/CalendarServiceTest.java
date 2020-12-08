//package ua.kpi.iasa.IASA_Organiser.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import ua.kpi.iasa.IASA_Organiser.data.GenericDataManager;
//import ua.kpi.iasa.IASA_Organiser.model.Event;
//import ua.kpi.iasa.IASA_Organiser.model.Priority;
//import ua.kpi.iasa.IASA_Organiser.model.Tag;
//import ua.kpi.iasa.IASA_Organiser.model.Type;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
//import static java.util.Arrays.asList;
//import static java.util.Collections.emptySet;
//import static java.util.Collections.singletonList;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.doCallRealMethod;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CalendarServiceTest {
//
//    @Mock
//    private GenericDataManager dataManager;
//
//    @Mock
//    private CalendarService calendarService;
//
//    @Mock
//    private Event event1;
//
//    @Mock
//    private Event event2;
//
//    @Mock
//    private Event event3;
//
//    @Test(expected = UnsupportedOperationException.class)
//    public void shouldGetCalendar() {
//        doCallRealMethod().when(calendarService).getCalendar();
//
//        calendarService.getCalendar();
//    }
//
//    @Test
//    public void shouldFindOneEventByDate() {
//        final LocalDate date1 = LocalDate.now();
//        final LocalDate date2 = LocalDate.MAX;
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getDate()).thenReturn(date2);
//        when(event2.getDate()).thenReturn(date2);
//        when(event3.getDate()).thenReturn(date1);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByDate(date1);
//
//        final List<Event> eventsByDate = calendarService.findEventsByDate(date1);
//
//        assertEquals(1, eventsByDate.size());
//        assertTrue(eventsByDate.contains(event3));
//    }
//
//    @Test
//    public void shouldFindAllEventsByDate() {
//        final LocalDate date1 = LocalDate.now();
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getDate()).thenReturn(date1);
//        when(event2.getDate()).thenReturn(date1);
//        when(event3.getDate()).thenReturn(date1);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByDate(date1);
//
//        final List<Event> eventsByDate = calendarService.findEventsByDate(date1);
//
//        assertEquals(3, eventsByDate.size());
//        assertTrue(eventsByDate.contains(event1));
//        assertTrue(eventsByDate.contains(event2));
//        assertTrue(eventsByDate.contains(event3));
//    }
//
//    @Test
//    public void shouldFindTwoEventsByDate() {
//        final LocalDate date1 = LocalDate.now();
//        final LocalDate date2 = LocalDate.MAX;
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getDate()).thenReturn(date1);
//        when(event2.getDate()).thenReturn(date2);
//        when(event3.getDate()).thenReturn(date1);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByDate(date1);
//
//        final List<Event> eventsByDate = calendarService.findEventsByDate(date1);
//
//        assertEquals(2, eventsByDate.size());
//        assertTrue(eventsByDate.contains(event1));
//        assertTrue(eventsByDate.contains(event3));
//    }
//
//    @Test
//    public void shouldFindOneEventByPriority() {
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getPriority()).thenReturn(Priority.HIGH);
//        when(event2.getPriority()).thenReturn(Priority.MEDIUM);
//        when(event3.getPriority()).thenReturn(Priority.LOW);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByPriority(Priority.LOW);
//
//        final List<Event> eventsByPriority = calendarService.findEventsByPriority(Priority.LOW);
//
//        assertEquals(1, eventsByPriority.size());
//        assertTrue(eventsByPriority.contains(event3));
//    }
//
//    @Test
//    public void shouldFindAllEventsByPriority() {
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getPriority()).thenReturn(Priority.HIGH);
//        when(event2.getPriority()).thenReturn(Priority.HIGH);
//        when(event3.getPriority()).thenReturn(Priority.HIGH);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByPriority(Priority.HIGH);
//
//        final List<Event> eventsByPriority = calendarService.findEventsByPriority(Priority.HIGH);
//
//        assertEquals(3, eventsByPriority.size());
//        assertTrue(eventsByPriority.contains(event1));
//        assertTrue(eventsByPriority.contains(event2));
//        assertTrue(eventsByPriority.contains(event3));
//    }
//
//    @Test
//    public void shouldFindTwoEventsByPriority() {
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getPriority()).thenReturn(Priority.HIGH);
//        when(event2.getPriority()).thenReturn(Priority.LOW);
//        when(event3.getPriority()).thenReturn(Priority.HIGH);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByPriority(Priority.HIGH);
//
//        final List<Event> eventsByPriority = calendarService.findEventsByPriority(Priority.HIGH);
//
//        assertEquals(2, eventsByPriority.size());
//        assertTrue(eventsByPriority.contains(event1));
//        assertTrue(eventsByPriority.contains(event3));
//    }
//
//    @Test
//    public void shouldSortEventsByPriorityWorstCase() {
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getPriority()).thenReturn(Priority.HIGH);
//        when(event2.getPriority()).thenReturn(Priority.MEDIUM);
//        when(event3.getPriority()).thenReturn(Priority.LOW);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).sortEventsByPriority();
//
//        final List<Event> sortedEvents = calendarService.sortEventsByPriority();
//
//        assertEquals(event3, sortedEvents.get(0));
//        assertEquals(event2, sortedEvents.get(1));
//        assertEquals(event1, sortedEvents.get(2));
//    }
//
//    @Test
//    public void shouldSortEventsByPriorityBestCase() {
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getPriority()).thenReturn(Priority.LOW);
//        when(event2.getPriority()).thenReturn(Priority.MEDIUM);
//        when(event3.getPriority()).thenReturn(Priority.HIGH);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).sortEventsByPriority();
//
//        final List<Event> sortedEvents = calendarService.sortEventsByPriority();
//
//        assertEquals(event1, sortedEvents.get(0));
//        assertEquals(event2, sortedEvents.get(1));
//        assertEquals(event3, sortedEvents.get(2));
//    }
//
//    @Test
//    public void shouldSortEventsByPriorityAverage() {
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getPriority()).thenReturn(Priority.HIGH);
//        when(event2.getPriority()).thenReturn(Priority.LOW);
//        when(event3.getPriority()).thenReturn(Priority.MEDIUM);
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).sortEventsByPriority();
//
//        final List<Event> sortedEvents = calendarService.sortEventsByPriority();
//
//        assertEquals(event2, sortedEvents.get(0));
//        assertEquals(event3, sortedEvents.get(1));
//        assertEquals(event1, sortedEvents.get(2));
//    }
//
//    @Test
//    public void shouldFindOneEventByTag() {
//        Tag tag1 = new Tag("test1");
//        Tag tag2 = new Tag("test2");
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getTags()).thenReturn(singletonList(tag2));
//        when(event2.getTags()).thenReturn(singletonList(tag2));
//        when(event3.getTags()).thenReturn(singletonList(tag1));
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByTag(tag1);
//
//        final List<Event> eventsByTag = calendarService.findEventsByTag(tag1);
//
//        assertEquals(1, eventsByTag.size());
//        assertTrue(eventsByTag.contains(event3));
//    }
//
//    @Test
//    public void shouldFindAllEventsByTag() {
//        Tag tag1 = new Tag("test1");
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getTags()).thenReturn(singletonList(tag1));
//        when(event2.getTags()).thenReturn(singletonList(tag1));
//        when(event3.getTags()).thenReturn(singletonList(tag1));
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByTag(tag1);
//
//        final List<Event> eventsByTag = calendarService.findEventsByTag(tag1);
//
//        assertEquals(3, eventsByTag.size());
//        assertTrue(eventsByTag.contains(event1));
//        assertTrue(eventsByTag.contains(event2));
//        assertTrue(eventsByTag.contains(event3));
//    }
//
//    @Test
//    public void shouldFindTwoEventsByTag() {
//        Tag tag1 = new Tag("test1");
//        Tag tag2 = new Tag("test2");
//        List<Event> testData = asList(event1, event2, event3);
//        when(event1.getTags()).thenReturn(singletonList(tag1));
//        when(event2.getTags()).thenReturn(singletonList(tag2));
//        when(event3.getTags()).thenReturn(singletonList(tag1));
//        when(calendarService.getDataManager()).thenReturn(dataManager);
//        when(dataManager.getAllEventsList()).thenReturn(testData);
//        doCallRealMethod().when(calendarService).findEventsByTag(tag1);
//
//        final List<Event> eventsByTag = calendarService.findEventsByTag(tag1);
//
//        assertEquals(2, eventsByTag.size());
//        assertTrue(eventsByTag.contains(event1));
//        assertTrue(eventsByTag.contains(event3));
//    }
//
//    @Test
//    public void shouldGetEmptyResultOnEventsMarkedAsExpired() {
//        LocalDate date = LocalDate.of(2020, 11, 22);
//        LocalTime time = LocalTime.of(22, 22);
//        when(calendarService.getCurrentDate()).thenReturn(LocalDate.of(2999, 1, 1));
//        when(calendarService.getCurrentTime()).thenReturn(LocalTime.of(0, 30));
//        when(event1.getTypes()).thenReturn(Set.of(Type.EXPIRED));
//        when(event2.getTypes()).thenReturn(Set.of(Type.EXPIRED));
//        when(event3.getTypes()).thenReturn(Set.of(Type.EXPIRED));
//        List<Event> dataList = asList(event1, event2, event3);
//        doCallRealMethod().when(calendarService).getExpiredEvents(dataList);
//
//        List<Event> result = calendarService.getExpiredEvents(dataList);
//
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void shouldGetResultOnEventsNotMarkedAsExpired() {
//        LocalDate date = LocalDate.of(2020, 11, 22);
//        LocalTime time = LocalTime.of(0, 30);
//        when(calendarService.getCurrentDate()).thenReturn(LocalDate.of(2999, 1, 1));
//        when(calendarService.getCurrentTime()).thenReturn(LocalTime.of(22, 22));
//        when(event1.getTypes()).thenReturn(emptySet());
//        when(event2.getTypes()).thenReturn(emptySet());
//        when(event3.getTypes()).thenReturn(emptySet());
//        when(event1.getDate()).thenReturn(date);
//        when(event2.getDate()).thenReturn(date);
//        when(event3.getDate()).thenReturn(date);
//        List<Event> dataList = asList(event1, event2, event3);
//        doCallRealMethod().when(calendarService).getExpiredEvents(dataList);
//
//        List<Event> result = calendarService.getExpiredEvents(dataList);
//
//        assertFalse(result.isEmpty());
//        assertEquals(3, result.size());
//        assertTrue(result.containsAll(dataList));
//    }
//
//    @Test
//    public void shouldGetResultOnEventsMixedMarkedAsExpired() {
//        LocalDate date = LocalDate.of(2020, 11, 22);
//        LocalTime time = LocalTime.of(0, 12);
//        when(calendarService.getCurrentDate()).thenReturn(LocalDate.of(2999, 1, 1));
//        when(calendarService.getCurrentTime()).thenReturn(LocalTime.of(0, 25));
//        when(event1.getTypes()).thenReturn(Set.of(Type.EXPIRED));
//        when(event2.getTypes()).thenReturn(emptySet());
//        when(event3.getTypes()).thenReturn(emptySet());
//        when(event2.getDate()).thenReturn(date);
//        when(event3.getDate()).thenReturn(date);
//        List<Event> dataList = asList(event1, event2, event3);
//        doCallRealMethod().when(calendarService).getExpiredEvents(dataList);
//
//        List<Event> result = calendarService.getExpiredEvents(dataList);
//
//        assertFalse(result.isEmpty());
//        assertEquals(2, result.size());
//        assertTrue(result.contains(event2));
//        assertTrue(result.contains(event3));
//    }
//
//    @Test
//    public void shouldGetResultOnEmptyEvents() {
//        when(calendarService.getCurrentDate()).thenReturn(LocalDate.of(2999, 1, 1));
//        when(calendarService.getCurrentTime()).thenReturn(LocalTime.of(0, 30));
//        List<Event> dataList = Collections.emptyList();
//        doCallRealMethod().when(calendarService).getExpiredEvents(dataList);
//
//        List<Event> result = calendarService.getExpiredEvents(dataList);
//
//        assertTrue(result.isEmpty());
//    }
//
//}
