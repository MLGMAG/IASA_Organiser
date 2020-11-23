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

public class EventBuilder {
    private UUID id;
    private String name;
    private Place place;
    private List<Human> invited;
    private LocalDate date;
    private LocalTime time;
    private Priority priority;
    private List<Tag> tags;
    private LocalTime duration;
    private List<Link> links;
    private boolean expired;
    private boolean single;
    private boolean periodic;
    private boolean deadline;
    private boolean completable;

    public EventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public EventBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder setPlace(Place place) {
        this.place = place;
        return this;
    }

    public EventBuilder setInvited(List<Human> invited) {
        this.invited = invited;
        return this;
    }

    public EventBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public EventBuilder setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public EventBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public EventBuilder setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public EventBuilder setDuration(LocalTime duration) {
        this.duration = duration;
        return this;
    }

    public EventBuilder setLinks(List<Link> links) {
        this.links = links;
        return this;
    }

    public EventBuilder setExpired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public EventBuilder setSingle(boolean single) {
        this.single = single;
        return this;
    }

    public EventBuilder setPeriodic(boolean periodic) {
        this.periodic = periodic;
        return this;
    }

    public EventBuilder setDeadline(boolean deadline) {
        this.deadline = deadline;
        return this;
    }

    public EventBuilder setCompletable(boolean completable) {
        this.completable = completable;
        return this;
    }

    public Event build() {
        return new Event(id, name, place, invited, date, time, priority, tags, duration, links, expired, single, periodic, deadline, completable);
    }

    public void setInitValues(Event event) {
        this.setId(event.getId());
        this.setName(event.getName());
        this.setPlace(event.getPlace());
        this.setInvited(event.getInvited());
        this.setDate(event.getDate());
        this.setTime(event.getTime());
        this.setPriority(event.getPriority());
        this.setTags(event.getTags());
        this.setDuration(event.getDuration());
        this.setLinks(event.getLinks());
        this.setExpired(event.isExpired());
        this.setSingle(event.isSingle());
        this.setPeriodic(event.isPeriodic());
        this.setDeadline(event.isDeadline());
        this.setCompletable(event.isCompletable());
    }

}
