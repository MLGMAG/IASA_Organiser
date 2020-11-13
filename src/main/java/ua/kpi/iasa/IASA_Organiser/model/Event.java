package ua.kpi.iasa.IASA_Organiser.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private boolean expired;
    private boolean single;
    private boolean periodic;
    private boolean deadline;
    private boolean completable;

    public Event(UUID id, String name, Place place, List<Human> invited, LocalDate date, LocalTime time, Priority priority, List<Tag> tags, LocalTime duration, List<Link> links, boolean expired, boolean single, boolean periodic, boolean deadline, boolean completable) {
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
        this.expired = expired;
        this.single = single;
        this.periodic = periodic;
        this.deadline = deadline;
        this.completable = completable;
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
        this.periodic = event.periodic;
        this.completable = event.completable;
        this.deadline = event.deadline;
        this.expired = event.expired;
        this.single = event.single;
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

    public boolean isExpired() {
        return expired;
    }

    public boolean isSingle() {
        return single;
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public boolean isDeadline() {
        return deadline;
    }

    public boolean isCompletable() {
        return completable;
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
        return expired == event.expired &&
                single == event.single &&
                periodic == event.periodic &&
                deadline == event.deadline &&
                completable == event.completable &&
                Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(place, event.place) &&
                Objects.equals(invited, event.invited) &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time) &&
                priority == event.priority &&
                Objects.equals(tags, event.tags) &&
                Objects.equals(duration, event.duration) &&
                Objects.equals(links, event.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place, invited, date, time, priority, tags, duration, links, expired, single, periodic, deadline, completable);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                "\n name='" + name + '\'' +
                "\n place=" + place +
                "\n invited=" + invited +
                "\n date=" + date +
                "\n time=" + time +
                "\n priority=" + priority +
                "\n tags=" + tags +
                "\n duration=" + duration +
                "\n links=" + links +
                "\n expired=" + expired +
                "\n single=" + single +
                "\n periodic=" + periodic +
                "\n deadline=" + deadline +
                "\n completable=" + completable +
                '}';
    }
}
