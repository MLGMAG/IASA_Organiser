package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

public class Event implements Comparator<Event>, Serializable {
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
    private boolean expire;
    private boolean periodic;
    private boolean completable;
    private boolean deadline;


    public Event(String name, Place place,
                 LocalDate date, LocalTime time, Priority priority, LocalTime duration) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.duration = duration;
    }

    public Event() {
    }  //TODO: remove this constructor for testing for next lab

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                '}';
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
        return expire == event.expire &&
                periodic == event.periodic &&
                completable == event.completable &&
                deadline == event.deadline &&
                Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(place, event.place) &&
                Arrays.equals(invited, event.invited) &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time) &&
                priority == event.priority &&
                Arrays.equals(tags, event.tags) &&
                Objects.equals(duration, event.duration) &&
                Arrays.equals(links, event.links) &&
                type == event.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, place, date, time, priority, duration, type, expire, periodic, completable, deadline);
        result = 31 * result + Arrays.hashCode(invited);
        result = 31 * result + Arrays.hashCode(tags);
        result = 31 * result + Arrays.hashCode(links);
        return result;
    }
}
