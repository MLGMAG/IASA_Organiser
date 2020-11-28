package ua.kpi.iasa.IASA_Organiser.builder;

import ua.kpi.iasa.IASA_Organiser.model.Event;
import ua.kpi.iasa.IASA_Organiser.model.Human;
import ua.kpi.iasa.IASA_Organiser.model.Link;
import ua.kpi.iasa.IASA_Organiser.model.Place;
import ua.kpi.iasa.IASA_Organiser.model.Priority;
import ua.kpi.iasa.IASA_Organiser.model.Tag;
import ua.kpi.iasa.IASA_Organiser.model.Type;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
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
    private Set<Type> types;

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

    public EventBuilder setTypes(Set<Type> types) {
        this.types = types;
        return this;
    }

    public Event build() {
        return new Event(id, name, place, invited, date, time, priority, tags, duration, links, types);
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
        this.setTypes(event.getTypes());
    }

}
