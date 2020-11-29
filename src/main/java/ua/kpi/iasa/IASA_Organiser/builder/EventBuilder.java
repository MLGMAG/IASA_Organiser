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

/**
 * EventBuilder is a class, which realized a Builder pattern GoF,
 * that allows to create an Event object and set full possible state
 * for it. Has the same fields as Event.
 *
 * @author Andrij Makrushin
 */
public class EventBuilder {
    /**
     * id, that allows to identify Event in Database.
     */
    private UUID id;

    /**
     * Event name.
     */
    private String name;

    /**
     * Place, where event is going to happen.
     */
    private Place place;

    /**
     * List of Human objects: invited people to this event.
     */
    private List<Human> invited;

    /**
     * Event`s date.
     */
    private LocalDate date;

    /**
     * Time, when event starts.
     */
    private LocalTime time;

    /**
     * Event`s priority level.
     */
    private Priority priority;

    /**
     * List of tags, associated with the event.
     */
    private List<Tag> tags;

    /**
     * Duration of event.
     */
    private LocalTime duration;

    /**
     * List of links, associated with the event.
     */
    private List<Link> links;

    /**
     * Set of event types.
     */
    private Set<Type> types;

    /**
     * Method sets id {@link EventBuilder#id} and returns
     * current EventBuilder object.
     *
     * @param id - id field
     * @return this object
     */
    public EventBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Method sets name {@link EventBuilder#name} and returns
     * current EventBuilder object.
     *
     * @param name - name field
     * @return this object
     */
    public EventBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method sets place {@link EventBuilder#place} and returns
     * current EventBuilder object.
     *
     * @param place - place field
     * @return this object
     */
    public EventBuilder setPlace(Place place) {
        this.place = place;
        return this;
    }

    /**
     * Method sets invited {@link EventBuilder#invited} and returns
     * current EventBuilder object.
     *
     * @param invited - invited field
     * @return this object
     */
    public EventBuilder setInvited(List<Human> invited) {
        this.invited = invited;
        return this;
    }

    /**
     * Method sets date {@link EventBuilder#date} and returns
     * current EventBuilder object.
     *
     * @param date - date field
     * @return this object
     */
    public EventBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Method sets time {@link EventBuilder#time} and returns
     * current EventBuilder object.
     *
     * @param time - time field
     * @return this object
     */
    public EventBuilder setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    /**
     * Method sets priority {@link EventBuilder#priority} and returns
     * current EventBuilder object.
     *
     * @param priority - priority field
     * @return this object
     */
    public EventBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Method sets tags {@link EventBuilder#tags} and returns
     * current EventBuilder object.
     *
     * @param tags - tags field
     * @return this object
     */
    public EventBuilder setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Method sets duration {@link EventBuilder#duration} and returns
     * current EventBuilder object.
     *
     * @param duration - duration field
     * @return this object
     */
    public EventBuilder setDuration(LocalTime duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Method sets links {@link EventBuilder#links} and returns
     * current EventBuilder object.
     *
     * @param links - links field
     * @return this object
     */
    public EventBuilder setLinks(List<Link> links) {
        this.links = links;
        return this;
    }

    /**
     * Method sets types {@link EventBuilder#types} and returns
     * current EventBuilder object.
     *
     * @param types - types field
     * @return this object
     */
    public EventBuilder setTypes(Set<Type> types) {
        this.types = types;
        return this;
    }

    /**
     * Method create Event object using it`s own fields.
     *
     * @return new Event object
     * @see Event
     */
    public Event build() {
        return new Event(id, name, place, invited, date, time, priority, tags, duration, links, types);
    }

    /**
     * Method sets own fields from input Event.
     *
     * @param event - source of copying fields
     * @see Event
     */
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
