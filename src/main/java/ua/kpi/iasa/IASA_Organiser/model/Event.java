package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.List;
import java.util.UUID;

public class Event implements Comparator<Event>, Serializable {
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
    private Type type;

    public Event(UUID id, String name, Place place, List<Human> invited, LocalDate date, LocalTime time, Priority priority, List<Tag> tags, LocalTime duration, List<Link> links) {
        this.id = id;
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

    private Event(Event event) {     //constructor for cloning(Prototype pattern)
        this.id = event.id;
        this.name = event.name;
        this.place = event.place;
        this.invited = event.invited;
        this.date = event.date;
        this.time = event.time;
        this.priority = event.priority;
        this.tags = event.tags;
        this.duration = event.duration;
        this.links = event.links;
        this.type = event.type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Place getPlace() {
        return place;
    }

    public List<Human> getInvited() {
        return invited;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Priority getPriority() {
        return priority;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public Event prototype() {
        return new Event(this);
    }

    @Override
    public int compare(Event event1, Event event2) {
        int yearDif = event1.getDate().getYear() - event2.getDate().getYear();
        int dayDif = event1.getDate().getDayOfYear() - event2.getDate().getDayOfYear();
        int hourDif = event1.getTime().getHour() - event2.getTime().getHour();
        int minuteDif = event1.getTime().getMinute() - event2.getTime().getMinute();
        int secondDif = event1.getTime().getSecond() - event2.getTime().getSecond();
        if (yearDif != 0) {
            return yearDif;
        } else if (dayDif != 0) {
            return dayDif;
        } else if (hourDif != 0) {
            return hourDif;
        } else if (minuteDif != 0) {
            return minuteDif;
        } else {
            return secondDif;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(place, event.place) &&
                Objects.equals(invited, event.invited) &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time) &&
                priority == event.priority &&
                Objects.equals(tags, event.tags) &&
                Objects.equals(duration, event.duration) &&
                Objects.equals(links, event.links) &&
                type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place, invited, date, time, priority, tags, duration, links, type);
    }
}
