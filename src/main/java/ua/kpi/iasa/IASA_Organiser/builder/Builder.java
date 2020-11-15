package ua.kpi.iasa.IASA_Organiser.builder;

import ua.kpi.iasa.IASA_Organiser.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface Builder {

    void setId(UUID id);

    void setName(String name);

    void setPlace(Place place);

    void setInvited(List<Human> invited);

    void setDate(LocalDate date);

    void setTime(LocalTime time);

    void setPriority(Priority priority);

    void setTags(List<Tag> tags);

    void setDuration(LocalTime duration);

    void setLinks(List<Link> links);

    void setSingle(boolean single);

    void setExpired(boolean expired);

    void setCompletable(boolean completable);

    void setPeriodic(boolean periodic);

    void setDeadline(boolean deadline);

    Event getResult();

    void setInitValues(Event event);

}