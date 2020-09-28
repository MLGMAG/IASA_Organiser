package ua.kpi.iasa.IASA_Organiser.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Event {
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

    public Event(String name, Place place, Human[] invited,
                 LocalDate date, LocalTime time, Priority priority,
                 Tag[] tags, LocalTime duration, Link[] links) {
        this.name = name;
        this.place = place;
        this.invited = invited;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.tags = tags;
        this.duration = duration;
        this.links = links;
    }
    public Event(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Human[] getInvited() {
        return invited;
    }

    public void setInvited(Human[] invited) {
        this.invited = invited;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
    
    public Link[] getLinks() {
        return links;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                '}';
    }
}
