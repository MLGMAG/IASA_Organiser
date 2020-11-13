package ua.kpi.iasa.IASA_Organiser.builders;

import ua.kpi.iasa.IASA_Organiser.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class BuilderDirector {
    private Builder startModification(String name, Place place, List<Human> invited,
                                      LocalDate date, LocalTime time, Priority priority,
                                      List<Tag> tags, LocalTime duration, List<Link> links){
        Builder builder = new EventBuilder();
        builder.setId(UUID.randomUUID());
        builder.setName(name);
        builder.setPlace(place);
        builder.setInvited(invited);
        builder.setDate(date);
        builder.setTime(time);
        builder.setPriority(priority);
        builder.setTags(tags);
        builder.setDuration(duration);
        builder.setLinks(links);
        return builder;
    }

    public Event getSingleEvent(String name, Place place, List<Human> invited,
                                LocalDate date, LocalTime time, Priority priority,
                                List<Tag> tags, LocalTime duration, List<Link> links){
        Builder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        builder.setSingle(true);
        return builder.getResult();
    }

    public Event getExpiredEvent(String name, Place place, List<Human> invited,
                                 LocalDate date, LocalTime time, Priority priority,
                                 List<Tag> tags, LocalTime duration, List<Link> links){
        Builder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        builder.setExpired(true);
        return builder.getResult();
    }

    public Event getPeriodicEvent(String name, Place place, List<Human> invited,
                                  LocalDate date, LocalTime time, Priority priority,
                                  List<Tag> tags, LocalTime duration, List<Link> links){
        Builder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        builder.setPeriodic(true);
        return builder.getResult();
    }

    public Event getCompletableEvent(String name, Place place, List<Human> invited,
                                     LocalDate date, LocalTime time, Priority priority,
                                     List<Tag> tags, LocalTime duration, List<Link> links){
        Builder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        builder.setCompletable(true);
        return builder.getResult();
    }

    public Event getDeadlineEvent(String name, Place place, List<Human> invited,
                                  LocalDate date, LocalTime time, Priority priority,
                                  List<Tag> tags, LocalTime duration, List<Link> links){
        Builder builder = startModification(name, place, invited, date, time, priority, tags, duration, links);
        builder.setDeadline(true);
        return builder.getResult();
    }
}
