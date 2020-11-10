package ua.kpi.iasa.IASA_Organiser.service;

import ua.kpi.iasa.IASA_Organiser.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventBuilder implements Builder {
    private UUID id;
    private String name;
    private Place place;
    private Human[] invited;
    private LocalDate date;
    private LocalTime time;
    private Priority priority;
    private Tag[] tags;
    private LocalTime duration;
    private Link[] links;
    private Type type;

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public void setInvited(Human[] invited) {
        this.invited = invited;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    @Override
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public void setLinks(Link[] links) {
        this.links = links;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }


    @Override
    public Event getResult() {
        return new Event(id, name, place, invited, date, time, priority, tags, duration, links);
    }
}
