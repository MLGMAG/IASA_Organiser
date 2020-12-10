package ua.kpi.iasa.IASA_Organiser.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "event")
@Entity(name = "event")
public class Event implements Comparator<Event>, Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "event_id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "event_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "event_place",
            joinColumns =
                    {@JoinColumn(name = "event_id", referencedColumnName = "event_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "place_id", referencedColumnName = "place_id")})
    private Place place;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "event_human", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "event_id"))
    private List<Human> invited;

    @Column(name = "event_date")
    private LocalDate date;

    @Column(name = "event_time")
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "event_tag", joinColumns = @JoinColumn(name = "event_id"))
    private List<Tag> tags;

    @Column(name = "event_duration")
    private LocalTime duration;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "event_link", joinColumns = @JoinColumn(name = "event_id"))
    private List<Link> links;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @JoinTable(name = "event_type", joinColumns = @JoinColumn(name = "event_id"))
    private Set<Type> types;

    public Event() {
    }

    public Event(UUID id, String name, Place place, List<Human> invited, LocalDate date, LocalTime time, Priority priority, List<Tag> tags, LocalTime duration, List<Link> links, Set<Type> types) {
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
        this.types = types;
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

    public List<Human> getInvited() {
        return invited;
    }

    public void setInvited(List<Human> invited) {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
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
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place, invited, date, time, priority, tags, duration, links, types);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", invited=" + invited +
                ", date=" + date +
                ", time=" + time +
                ", priority=" + priority +
                ", tags=" + tags +
                ", duration=" + duration +
                ", links=" + links +
                ", types=" + types +
                '}';
    }
}
