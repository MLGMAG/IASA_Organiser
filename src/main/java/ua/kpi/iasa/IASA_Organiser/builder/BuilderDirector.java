package ua.kpi.iasa.IASA_Organiser.builder;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class BuilderDirector {
    private EventBuilder startModification(String name, Place place, List<Human> invited,
                                           LocalDate date, LocalTime time, Priority priority,
                                           List<Tag> tags, LocalTime duration, List<Link> links) {
        return new EventBuilder().setId(UUID.randomUUID())
                .setName(name)
                .setPlace(place)
                .setInvited(invited)
                .setDate(date)
                .setTime(time)
                .setPriority(priority)
                .setTags(tags)
                .setDuration(duration)
                .setLinks(links);
    }

    public Event getSingleEvent(String name, Place place, List<Human> invited,
                                LocalDate date, LocalTime time, Priority priority,
                                List<Tag> tags, LocalTime duration, List<Link> links) {
        EventBuilder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        return builder.setSingle(true).build();
    }

    public Event getExpiredEvent(String name, Place place, List<Human> invited,
                                 LocalDate date, LocalTime time, Priority priority,
                                 List<Tag> tags, LocalTime duration, List<Link> links) {
        EventBuilder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        return builder.setExpired(true).build();
    }

    public Event getPeriodicEvent(String name, Place place, List<Human> invited,
                                  LocalDate date, LocalTime time, Priority priority,
                                  List<Tag> tags, LocalTime duration, List<Link> links) {
        EventBuilder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        return builder.setPeriodic(true).build();
    }

    public Event getCompletableEvent(String name, Place place, List<Human> invited,
                                     LocalDate date, LocalTime time, Priority priority,
                                     List<Tag> tags, LocalTime duration, List<Link> links) {
        EventBuilder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        return builder.setCompletable(true).build();
    }

    public Event getDeadlineEvent(String name, Place place, List<Human> invited,
                                  LocalDate date, LocalTime time, Priority priority,
                                  List<Tag> tags, LocalTime duration, List<Link> links) {
        EventBuilder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        return builder.setDeadline(true).build();
    }
}
